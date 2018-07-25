package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.application.mediadata.RegisterForMediaDataUpdatesCommand
import de.bringmeister.connect.product.application.search.UpdateSearchIndexCommand
import de.bringmeister.connect.product.application.shop.UpdateShopCommand
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.EventListener
import de.bringmeister.connect.product.domain.product.ProductCreatedEvent
import org.springframework.stereotype.Component

@Component
class ProductCreatedEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(event: ProductCreatedEvent) {

        commandBus.send(
            RegisterForMediaDataUpdatesCommand(
                productNumber = event.productNumber
            )
        )

        commandBus.send(
            UpdateShopCommand(
                productNumber = event.productNumber
            )
        )

        commandBus.send(
            UpdateSearchIndexCommand(
                productNumber = event.productNumber
            )
        )
    }
}
