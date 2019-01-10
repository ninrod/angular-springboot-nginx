package org.ninrod.blog.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired TestRestTemplate restTemplate;

    @Test
    public void assertNames() throws IOException {
        String s ="my_friend_stay_a_while_and_listen";
        String path = String.format("/?name=%s", s);
        ResponseEntity<String> entity = restTemplate.getForEntity(path, String.class);
        assertEquals("status code should be ok", entity.getStatusCode(), HttpStatus.OK);
        String actual = new ObjectMapper().readTree(entity.getBody()).get("content").asText();
        assertEquals("should contain the greeting", String.format("Hello, %s!", s),actual );
    }

    @Test
    public void usersRetriedved() throws IOException{
        String path = "/users";
        ResponseEntity<String> e = restTemplate.getForEntity(path, String.class);
        assertEquals("should be ok", e.getStatusCode(), HttpStatus.OK);
        System.out.println(e.getBody());
        String actual = new ObjectMapper().readTree(e.getBody()).get(1).get("firstname").asText();
        assertEquals("should contain second", "second", actual);
    }
}