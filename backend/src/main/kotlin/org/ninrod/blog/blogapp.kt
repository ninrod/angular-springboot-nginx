package org.ninrod.blog

import org.slf4j.Logger

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import javax.annotation.PostConstruct

@SpringBootApplication
class BlogApplication {
    companion object {
        val logger: Logger? = LoggerFactory.getLogger(BlogApplication::class.java)
    }
    @PostConstruct
    fun postConstruct() {
        logger?.info("-------------------------Blog Initializing---------------------")
    }
}
