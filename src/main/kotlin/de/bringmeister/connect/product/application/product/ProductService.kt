package de.bringmeister.connect.product.application.product

import de.bringmeister.connect.product.domain.CommandListener
import de.bringmeister.connect.product.domain.DomainEventBus
import de.bringmeister.connect.product.domain.product.CreateNewProductCommand
import de.bringmeister.connect.product.domain.product.Product
import de.bringmeister.connect.product.domain.product.ProductNumber
import de.bringmeister.connect.product.domain.product.ProductRepository
import de.bringmeister.connect.product.domain.product.UpdateMasterDataCommand
import de.bringmeister.connect.product.domain.product.UpdateMediaDataCommand
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val domainEventBus: DomainEventBus
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @CommandListener
    fun handle(command: CreateNewProductCommand) {
        val product = Product(command)
        productRepository.save(product)
        domainEventBus.sendAll(product.occurredEvents())
    }

    @CommandListener
    fun handle(command: UpdateMasterDataCommand) {
        val productNumber = ProductNumber(command.productNumber)
        val product = productRepository.find(productNumber)
        product.updateMasterData(command)
        productRepository.save(product)
        domainEventBus.sendAll(product.occurredEvents())
    }

    @CommandListener
    fun handle(command: UpdateMediaDataCommand) {
        val productNumber = ProductNumber(command.productNumber)
        if (productRepository.exists(productNumber)) {
            val product = productRepository.find(productNumber)
            product.updateMediaData(command)
            productRepository.save(product)
            domainEventBus.sendAll(product.occurredEvents())
        } else {
            log.info("Media data ignored as product doesn't exist. [productNumber={}]", productNumber)
        }
    }
}