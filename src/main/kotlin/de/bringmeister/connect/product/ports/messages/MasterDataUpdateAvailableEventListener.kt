package de.bringmeister.connect.product.ports.messages

import com.google.common.eventbus.Subscribe
import de.bringmeister.connect.product.domain.CommandBus
import de.bringmeister.connect.product.domain.product.CreateNewProductCommand
import de.bringmeister.connect.product.domain.product.ProductNumber
import de.bringmeister.connect.product.domain.product.ProductRepository
import de.bringmeister.connect.product.domain.product.UpdateMasterDataCommand
import de.bringmeister.connect.product.ports.rest.MasterDataUpdateAvailableEvent
import org.springframework.stereotype.Component

@Component
class MasterDataUpdateAvailableEventListener(private val commandBus: CommandBus,
                                             private val productRepository: ProductRepository) {

    @Subscribe
    fun handle(domainEvent: MasterDataUpdateAvailableEvent) {

        Thread.sleep(5000)

        val productNumber = ProductNumber(domainEvent.articleNumber)
        val productExists = productRepository.exists(productNumber)

        if (productExists) {

            commandBus.send(UpdateMasterDataCommand(
                productNumber = productNumber.productNumber,
                name = domainEvent.name,
                description = domainEvent.description
            ))

        } else {

            commandBus.send(CreateNewProductCommand(
                productNumber = productNumber.productNumber,
                name = domainEvent.name,
                description = domainEvent.description
            ))
        }
    }
}
