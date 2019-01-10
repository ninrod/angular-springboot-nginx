package org.ninrod.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private UserRepository userRepo;
    private UserService userService;
    private PhraseService service;

    @Autowired
    public GreetingController(
            UserRepository userRepo,
            PhraseService service,
            UserService userService
    ) {
        this.userRepo = userRepo;
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), service.phrase() + name + "!");
    }

    @RequestMapping(path = "/persist", method = RequestMethod.POST)
    public void persist() {
        Usuario juergen = new Usuario("springjuergen", "Juergen", "Hoeller", "blah");
        log.info("trying to persist ${juergen.firstname}");
        userRepo.save(juergen);
        log.info("${juergen.firstname} was persisted");
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Usuario> users() {
        log.info("trying to get users");
        List<Usuario> users = userService.getUsers();
        log.info("finishing users method");
        return users;
    }
}

