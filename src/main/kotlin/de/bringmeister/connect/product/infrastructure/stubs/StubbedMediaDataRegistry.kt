package de.bringmeister.connect.product.infrastructure.stubs

import de.bringmeister.connect.product.application.mediadata.MediaDataRegistry
import de.bringmeister.connect.product.application.mediadata.RegisterForMediaDataUpdatesCommand
import de.bringmeister.connect.product.domain.EventBus
import de.bringmeister.connect.product.ports.rest.MediaDataUpdateAvailableEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StubbedMediaDataRegistry(private val eventBus: EventBus) : MediaDataRegistry {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: RegisterForMediaDataUpdatesCommand) {

        log.info("Registered for media data updates. [productNumber={}]", command.productNumber)

        // This event simulates the response of another external system. After
        // a product has been registered for updates at the media data service,
        // this service will eventually send an update to us.

        eventBus.send(
            MediaDataUpdateAvailableEvent(
                productNumber = command.productNumber,
                imageUrl = "www.my-domain.com/my-new-image.jpg"
            )
        )
    }
}
