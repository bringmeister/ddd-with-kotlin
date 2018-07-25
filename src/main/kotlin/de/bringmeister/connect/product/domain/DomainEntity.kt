package de.bringmeister.connect.product.domain

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * The abstract skeleton of a domain entity. A domain entity is an object
 * of the Domain Driven Design. It encapsulates data and behaviour. This
 * means it not only holds data, but also contains the business methods in
 * order to manipulate it. After data has been manipulated, a domain event
 * will be raised. Other contexts can listen and react on this event. This
 * abstract class provides convenient methods to handle those events.
 *
 * Every domain entity has an unique ID. This means that the identity of a
 * domain entity is based on its ID rather than on the current data. To
 * reflect this concept in the code, we've overwritten equals() and hashCode().
 */
abstract class DomainEntity<T : Any>(val id: T) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    private val occurredEvents: MutableList<Event> = mutableListOf()

    fun occurredEvents(): List<Event> {
        val events = this.occurredEvents.toMutableList()
        this.occurredEvents.clear()
        log.trace("Return occurred domain events. [numberOfEvents=${events.size}]")
        return events
    }

    protected fun raise(event: Event) {
        occurredEvents.add(event)
        log.debug("Raised new domain event. [type=${event::class.simpleName}]")
    }

    override fun equals(other: Any?): Boolean {
        if (other!!.javaClass != this.javaClass) {
            return false
        }
        return EqualsBuilder()
            .append(this.id, (other as DomainEntity<*>).id) // Only on the ID!
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder()
            .append(id) // Only on the ID!
            .toHashCode()
    }
}
