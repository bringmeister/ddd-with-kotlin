package de.bringmeister.connect.product.ports.messages

import com.google.common.eventbus.Subscribe
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.product.ProductCreatedEvent
import de.bringmeister.connect.product.application.mediadata.RegisterForMediaDataUpdatesCommand
import org.springframework.stereotype.Component

@Component
class ProductCreatedEventListener(private val commandBus: CommandBus) {

    @Subscribe
    fun handle(domainEvent: ProductCreatedEvent) {
        val requestEnrichmentDataCommand = RegisterForMediaDataUpdatesCommand(domainEvent.productNumber)
        commandBus.send(requestEnrichmentDataCommand)
    }
}
