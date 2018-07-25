package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.application.cdn.UpdateCdnCommand
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.EventListener
import de.bringmeister.connect.product.domain.product.MediaDataUpdatedEvent
import org.springframework.stereotype.Component

@Component
class MediaDataUpdatedEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(event: MediaDataUpdatedEvent) {

        val updateCdnCommand = UpdateCdnCommand(event.productNumber)
        commandBus.send(updateCdnCommand)
    }
}
