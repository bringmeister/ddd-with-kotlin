package de.bringmeister.connect.product.domain

interface DomainEventBus {
    fun send(domainEvent: DomainEvent)

    fun sendAll(domainEvents: List<DomainEvent>) {
        domainEvents.forEach(this::send)
    }
}
