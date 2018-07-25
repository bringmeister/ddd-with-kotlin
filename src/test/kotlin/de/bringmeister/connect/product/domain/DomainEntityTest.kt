package de.bringmeister.connect.product.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DomainEntityTest {

    @Test
    fun `should equal domain entity of same type with same ID`() {
        val entity1 = MyDomainEntityA("Jon")
        val entity2 = MyDomainEntityA("Jon")

        assertThat(entity1).isEqualTo(entity2)
    }

    @Test
    fun `should not equal domain entity of different type`() {
        val entityA = MyDomainEntityA("Jon")
        val entityB = MyDomainEntityB("Jon")

        assertThat(entityA).isNotEqualTo(entityB)
    }

    @Test
    fun `should return events`() {

        val entity = MyDomainEntityA("Jon")
        entity.doSomething()

        val events = entity.occurredEvents()
        assertThat(events).hasSize(1)
    }

    @Test
    fun `should return events only once`() {

        val entity = MyDomainEntityA("Jon")
        entity.doSomething()

        val events1 = entity.occurredEvents()
        val events2 = entity.occurredEvents()

        assertThat(events1).hasSize(1) // Events are returned once...
        assertThat(events2).hasSize(0) // ...but not twice!
    }

    private class MyDomainEvent : Event

    private class MyDomainEntityA(name: String) : DomainEntity<String>(name) {

        fun doSomething() {
            raise(MyDomainEvent())
        }
    }

    private class MyDomainEntityB(name: String) : DomainEntity<String>(name)
}
