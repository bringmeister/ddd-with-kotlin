package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.application.mediadata.RegisterForMediaDataUpdatesCommand
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.product.ProductCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ProductCreatedEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(domainEvent: ProductCreatedEvent) {
        val requestEnrichmentDataCommand = RegisterForMediaDataUpdatesCommand(domainEvent.productNumber)
        commandBus.send(requestEnrichmentDataCommand)
    }
}
