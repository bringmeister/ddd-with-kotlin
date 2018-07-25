package de.bringmeister.connect.product

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * This test will run the complete Spring Boot application. It
 * will make sure that all beans and configurations are found
 * and that the application will start.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class SpringContextTest {

    @Test
    fun `should start app`() {
        // empty as only the start up is tested
    }
}
