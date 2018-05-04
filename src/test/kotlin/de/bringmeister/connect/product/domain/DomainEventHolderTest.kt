package de.bringmeister.connect.product.domain

import de.bringmeister.connect.product.domain.product.ProductCreatedEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DomainEventHolderTest {

    @Test
    fun `should raise domain event`() {

        val domainEventHolder = DomainEventHolder()
        domainEventHolder.raise(ProductCreatedEvent(productNumber = "100001"))
        domainEventHolder.raise(ProductCreatedEvent(productNumber = "100002"))

        val events = domainEventHolder.occurredEvents()
        assertThat(events).hasSize(2)
        assertThat(events[0]).isEqualTo(ProductCreatedEvent(productNumber = "100001"))
        assertThat(events[1]).isEqualTo(ProductCreatedEvent(productNumber = "100002"))
    }

    @Test
    fun `should clear events after returning them`() {

        val domainEventHolder = DomainEventHolder()
        domainEventHolder.raise(ProductCreatedEvent(productNumber = "100001"))
        domainEventHolder.raise(ProductCreatedEvent(productNumber = "100002"))

        assertThat(domainEventHolder.occurredEvents()).hasSize(2)
        assertThat(domainEventHolder.occurredEvents()).hasSize(0) // list is empty now!
    }
}