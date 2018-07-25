package de.bringmeister.connect.product.infrastructure.spring

import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.CommandListener
import de.bringmeister.connect.product.infrastructure.spring.SpringCommandBusTest.MyCommandHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [SpringCommandBus::class, MyCommandHandler::class])
class SpringCommandBusTest {

    @Autowired
    private lateinit var commandBus: CommandBus

    @Autowired
    private lateinit var myCommandHandler: MyCommandHandler

    companion object {
        val latch = CountDownLatch(1)
    }

    @Test
    fun `should send and receive command`() {
        commandBus.send(MyCommand("I'm a command!"))
        latch.await(5, SECONDS)
        assertThat(myCommandHandler.invoked).isTrue()
    }

    @Service
    class MyCommandHandler {

        var invoked = false

        @CommandListener
        fun handle(myCommand: MyCommand) {
            invoked = true
            assertThat(myCommand.data).isEqualTo("I'm a command!")
            latch.countDown()
        }
    }

    data class MyCommand(val data: String) : Command
}
