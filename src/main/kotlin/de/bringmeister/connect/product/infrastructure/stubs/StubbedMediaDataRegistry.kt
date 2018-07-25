package de.bringmeister.connect.product.infrastructure.stubs

import de.bringmeister.connect.product.application.mediadata.MediaDataRegistry
import de.bringmeister.connect.product.application.mediadata.RegisterForMediaDataUpdatesCommand
import de.bringmeister.connect.product.domain.DomainEventBus
import de.bringmeister.connect.product.ports.rest.MediaDataUpdateAvailableEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class StubbedMediaDataRegistry(private val domainEventBus: DomainEventBus) : MediaDataRegistry {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: RegisterForMediaDataUpdatesCommand) {

        log.info("Registered for media data updates. [productNumber={}]", command.productNumber)

        Thread.sleep(5000)

        domainEventBus.send(
            MediaDataUpdateAvailableEvent(
                productNumber = command.productNumber,
                imageUrl = "www.my-domain.com/my-new-image.jpg"
            )
        )
    }
}
