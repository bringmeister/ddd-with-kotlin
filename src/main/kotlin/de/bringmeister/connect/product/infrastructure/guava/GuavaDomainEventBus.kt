package de.bringmeister.connect.product.infrastructure.guava

import com.google.common.eventbus.AsyncEventBus
import de.bringmeister.connect.product.domain.DomainEvent
import de.bringmeister.connect.product.domain.DomainEventBus
import org.springframework.stereotype.Component

@Component
class GuavaDomainEventBus(private val bus: AsyncEventBus): DomainEventBus {

    override fun send(domainEvent: DomainEvent) {
        bus.post(domainEvent);
    }
}
