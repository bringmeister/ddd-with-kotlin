package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.EventListener
import de.bringmeister.connect.product.domain.product.UpdateMediaDataCommand
import de.bringmeister.connect.product.ports.rest.MediaDataUpdateAvailableEvent
import org.springframework.stereotype.Component

@Component
class MediaDataUpdateAvailableEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(event: MediaDataUpdateAvailableEvent) {

        commandBus.send(
            UpdateMediaDataCommand(
                productNumber = event.productNumber,
                imageUrl = event.imageUrl
            )
        )
    }
}

