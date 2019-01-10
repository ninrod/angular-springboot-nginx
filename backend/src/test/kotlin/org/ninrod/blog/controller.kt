package org.ninrod.blog

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.hamcrest.Matchers.`is`
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExtendWith(SpringExtension::class)
@WebMvcTest(GreetingController::class)
class ControllerTests(@Autowired val mvc: MockMvc,
                      @Autowired val service: PhraseService,
                      @Autowired val userService: UserService
                      ) {
    @Test
    fun `should return hello world`() {
        val phrase = "Hello, "
        `when`(service.phrase()).thenReturn(phrase)
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk)
                .andExpect(jsonPath("$.id", `is`(1)))
                .andExpect(jsonPath("$.content", `is`("${phrase}World")))
    }

    @Test
    fun `should return hello, ninrod`() {
        val phrase = "Olá, "
        `when`(service.phrase()).thenReturn(phrase)

        val exp = "NiNRoD"
        mvc.perform(get("/?name=$exp").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.content", `is`("$phrase$exp")))
    }

    @Test
    fun `should return second`() {
        val couves: String = "das couves"
        val users: List<Usuario> = listOf(Usuario("1", "first", "jose", couves))
        `when`(userService.getUsers()).thenReturn(users)

        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$[0].description", `is`(couves)))
    }

    @TestConfiguration
    class Config {
        @Bean fun repo(): UserRepository = mock(UserRepository::class.java)
        @Bean fun service(): PhraseService = mock(PhraseService::class.java)
        @Bean fun userService(): UserService = mock(UserService::class.java)
    }
}
