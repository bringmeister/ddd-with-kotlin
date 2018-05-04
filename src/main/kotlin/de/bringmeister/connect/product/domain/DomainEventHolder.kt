package de.bringmeister.connect.product.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class DomainEventHolder {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    private val occurredEvents: MutableList<DomainEvent> = mutableListOf()

    fun raise(domainEvent: DomainEvent) {
        occurredEvents.add(domainEvent)
        log.debug("Raised domain event. [domainEvent=${domainEvent::class.simpleName}]")
    }

    fun occurredEvents(): List<DomainEvent> {
        val events = this.occurredEvents.toMutableList()
        this.occurredEvents.clear()
        log.trace("Return occurred domain events. [#${events.size}]")
        return events
    }
}