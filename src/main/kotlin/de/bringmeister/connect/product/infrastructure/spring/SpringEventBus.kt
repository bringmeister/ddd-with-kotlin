package de.bringmeister.connect.product.infrastructure.spring

import de.bringmeister.connect.product.domain.Event
import de.bringmeister.connect.product.domain.EventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringEventBus(private val publisher: ApplicationEventPublisher) : EventBus {

    override fun send(event: Event) {
        publisher.publishEvent(event)
    }
}
