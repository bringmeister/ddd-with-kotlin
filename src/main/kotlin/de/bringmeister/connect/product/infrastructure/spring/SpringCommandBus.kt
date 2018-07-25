package de.bringmeister.connect.product.infrastructure.spring

import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.CommandBus
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class SpringCommandBus(private val publisher: ApplicationEventPublisher) : CommandBus {

    override fun send(command: Command) {
        publisher.publishEvent(command)
    }
}
