package org.ninrod.blog

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class SimpleTest {
    @Test
    fun simpleTest() {
        Assertions.assertEquals(4, 2 * 2)
    }
}
