package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.product.UpdateMediaDataCommand
import de.bringmeister.connect.product.ports.rest.MediaDataUpdateAvailableEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MediaDataUpdateAvailableEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(domainEvent: MediaDataUpdateAvailableEvent) {

        commandBus.send(UpdateMediaDataCommand(
            productNumber = domainEvent.productNumber,
            imageUrl = domainEvent.imageUrl
        ))
    }
}

