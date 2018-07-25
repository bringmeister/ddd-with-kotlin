package de.bringmeister.connect.product.application.product

import de.bringmeister.connect.product.domain.CommandListener
import de.bringmeister.connect.product.domain.EventBus
import de.bringmeister.connect.product.domain.product.CreateNewProductCommand
import de.bringmeister.connect.product.domain.product.Product
import de.bringmeister.connect.product.domain.product.ProductRepository
import de.bringmeister.connect.product.domain.product.UpdateMasterDataCommand
import de.bringmeister.connect.product.domain.product.UpdateMediaDataCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val eventBus: EventBus
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @CommandListener
    fun handle(command: CreateNewProductCommand) {
        val product = Product(command)
        productRepository.save(product)
        eventBus.sendAll(product.occurredEvents())
    }

    @CommandListener
    fun handle(command: UpdateMasterDataCommand) {
        val product = productRepository.find(command.productNumber)
        product.handle(command)
        productRepository.save(product)
        eventBus.sendAll(product.occurredEvents())
    }

    @CommandListener
    fun handle(command: UpdateMediaDataCommand) {
        if (productRepository.exists(command.productNumber)) {
            val product = productRepository.find(command.productNumber)
            product.handle(command)
            productRepository.save(product)
            eventBus.sendAll(product.occurredEvents())
        } else {
            log.info("Media data ignored as product doesn't exist. [productNumber={}]", command.productNumber)
        }
    }
}