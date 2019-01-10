package org.ninrod.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest(@Autowired val restTemplate: TestRestTemplate) {
    @Test
    fun `Assert blog page title, content and status code`() {
        val s ="my_friend_stay_a_while_and_listen"
        val entity = restTemplate.getForEntity<String>("/?name=$s")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("Hello, $s")
    }

    @Test
    fun `Assert users are retrieved`() {
        val e = restTemplate.getForEntity<String>("/users")
        assertThat(e.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(e.body).contains("second")
    }
}