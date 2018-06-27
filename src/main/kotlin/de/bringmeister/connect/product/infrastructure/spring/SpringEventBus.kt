package de.bringmeister.connect.product.infrastructure.spring

import de.bringmeister.connect.product.domain.DomainEvent
import de.bringmeister.connect.product.domain.DomainEventBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringEventBus(private val publisher: ApplicationEventPublisher): DomainEventBus {

    override fun send(domainEvent: DomainEvent) {
        publisher.publishEvent(domainEvent)
    }
}
