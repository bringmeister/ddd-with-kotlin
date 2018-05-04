package de.bringmeister.connect.product.ports.messages

import com.google.common.eventbus.Subscribe
import de.bringmeister.connect.product.application.cdn.UpdateCdnCommand
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.application.mediadata.MediaDataUpdatedEvent
import org.springframework.stereotype.Component

@Component
class MediaDataUpdatedEventListener(private val commandBus: CommandBus) {

    @Subscribe
    fun handle(domainEvent: MediaDataUpdatedEvent) {

        val updateCdnCommand = UpdateCdnCommand(domainEvent.productNumber)
        commandBus.send(updateCdnCommand)
    }
}
