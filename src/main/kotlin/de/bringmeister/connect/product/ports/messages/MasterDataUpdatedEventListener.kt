package de.bringmeister.connect.product.ports.messages

import de.bringmeister.connect.product.application.search.UpdateSearchIndexCommand
import de.bringmeister.connect.product.application.shop.UpdateShopCommand
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.EventListener
import de.bringmeister.connect.product.domain.product.MasterDataUpdatedEvent
import org.springframework.stereotype.Component

@Component
class MasterDataUpdatedEventListener(private val commandBus: CommandBus) {

    @EventListener
    fun handle(event: MasterDataUpdatedEvent) {

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
