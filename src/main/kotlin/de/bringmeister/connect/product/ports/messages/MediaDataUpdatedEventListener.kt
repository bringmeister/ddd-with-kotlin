package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.application.cdn.UpdateCdnCommand
import de.bringmeister.connect.product.application.mediadata.MediaDataUpdatedEvent
import de.bringmeister.connect.product.domain.CommandBus
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class MediaDataUpdatedEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(domainEvent: MediaDataUpdatedEvent) {

        val updateCdnCommand = UpdateCdnCommand(domainEvent.productNumber)
        commandBus.send(updateCdnCommand)
    }
}
