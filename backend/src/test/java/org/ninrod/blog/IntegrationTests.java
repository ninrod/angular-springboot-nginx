package org.ninrod.blog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired TestRestTemplate restTemplate;

    @Test
    public void assertNames() {
        String s ="my_friend_stay_a_while_and_listen";
        String path = "/?name=$s";
        ResponseEntity<String> entity = restTemplate.getForEntity(path, String.class);
        assertThat("status code should be ok", entity.getStatusCode().equals(HttpStatus.OK));
        assertThat("should contain ok", entity.getBody().contains("Hello, $s"));
    }

    @Test
    public void usersRetriedved() {
        String path = "/users";
        ResponseEntity<String> e = restTemplate.getForEntity(path, String.class);
        assertThat("should be ok", e.getStatusCode().equals(HttpStatus.OK));
        assertThat("should contain second", e.getBody().contains("second"));
    }
}