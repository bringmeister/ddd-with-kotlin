package de.bringmeister.connect.product.ports.messages

import com.google.common.eventbus.Subscribe
import de.bringmeister.connect.product.application.search.UpdateSearchIndexCommand
import de.bringmeister.connect.product.application.shop.UpdateShopCommand
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.product.MasterDataUpdatedEvent
import org.springframework.stereotype.Component

@Component
class MasterDataUpdatedEventListener(private val commandBus: CommandBus) {

    @Subscribe
    fun handle(domainEvent: MasterDataUpdatedEvent) {

        commandBus.send(UpdateShopCommand(
            productNumber = domainEvent.productNumber
        ))

        commandBus.send(UpdateSearchIndexCommand(
            productNumber = domainEvent.productNumber
        ))
    }
}
