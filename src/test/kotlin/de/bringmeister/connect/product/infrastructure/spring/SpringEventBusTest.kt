package de.bringmeister.connect.product.infrastructure.spring

import de.bringmeister.connect.product.domain.Event
import de.bringmeister.connect.product.domain.EventBus
import de.bringmeister.connect.product.domain.EventListener
import de.bringmeister.connect.product.infrastructure.spring.SpringEventBusTest.MyEventHandler
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
@ContextConfiguration(classes = [SpringEventBus::class, MyEventHandler::class])
class SpringEventBusTest {

    @Autowired
    private lateinit var eventBus: EventBus

    @Autowired
    private lateinit var myEventHandler: MyEventHandler

    companion object {
        val latch = CountDownLatch(1)
    }

    @Test
    fun `should send and receive command`() {
        eventBus.send(MyEvent("I'm an event!"))
        latch.await(5, SECONDS)
        assertThat(myEventHandler.invoked).isTrue()
    }

    @Service
    class MyEventHandler {

        var invoked = false

        @EventListener
        fun handle(myEvent: MyEvent) {
            invoked = true
            assertThat(myEvent.data).isEqualTo("I'm an event!")
            latch.countDown()
        }
    }

    data class MyEvent(val data: String) : Event
}
