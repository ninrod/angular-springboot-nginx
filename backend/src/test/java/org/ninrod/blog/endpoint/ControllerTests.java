package org.ninrod.blog.endpoint;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ninrod.blog.endpoint.MainController;
import org.ninrod.blog.greeting.PhraseService;
import org.ninrod.blog.user.UserRepository;
import org.ninrod.blog.user.UserService;
import org.ninrod.blog.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)
class ControllerTests {
    @Autowired private MockMvc mvc;
    @Autowired private PhraseService service;
    @Autowired private UserService userService;

    @Test
    void shouldReturnHello() throws Exception {
        String phrase = "Hello, ";
        when(service.phrase()).thenReturn(phrase);
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.content", is("${phrase}World")));
    }

    @Test
    void shouldReturnHelloNinrod() throws Exception {
        String phrase = "Olá, ";
        when(service.phrase()).thenReturn(phrase);
        String exp = "NiNRoD";
        mvc.perform(get("/?name=$exp").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", is("$phrase$exp")));
    }

    @Test
    void shouldReturnSecond() throws Exception {
        String couves = "das couves";
        Usuario joseDasCouves = new Usuario("1", "first", "jose", couves);
        List<Usuario> users = new ArrayList<Usuario>() {{
            add(joseDasCouves);
        }};

        when(userService.getUsers()).thenReturn(users);

        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description", is(couves)));
    }

    @TestConfiguration
    class Config {
        @Bean
        public UserRepository repo() {
          return mock(UserRepository.class);
        }

        @Bean
        public PhraseService service(){
            return mock(PhraseService.class);
        }

        @Bean
        public UserService userService() {
            return mock(UserService.class);
        }
    }
}
