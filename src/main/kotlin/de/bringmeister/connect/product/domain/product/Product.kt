package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.domain.DomainEntity
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.Assert

/**
 * The product domain entity.
 *
 * This entity encapsulates all information which belongs to a product.
 * It also provides business methods to work on this information. Data
 * cannot be changed from outside - there are no setters.
 *
 * Whenever data has been changed a domain event will thrown. This event
 * informs any listener that something has changed in the context of a
 * product. In a real life example, those events would be published over
 * a message broker such as Kafka, ActiveMQ or AWS Kinesis.
 */
class Product(command: CreateNewProductCommand) : DomainEntity<ProductNumber>(command.productNumber) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    var productInformation: ProductInformation
        private set

    var imageUrl: String? = null // we  have none until we get the first media data update
        private set

    init {

        Assert.hasText(command.name, "Product name must not be empty!")
        Assert.hasText(command.description, "Product description must not be empty!")

        productInformation = ProductInformation(
            name = command.name,
            description = command.description
        )

        raise(ProductCreatedEvent(productNumber = id))
        log.info("New product created. [productNumber={}]", id)
    }

    fun handle(command: UpdateMasterDataCommand) {

        Assert.hasText(command.name, "Product name must not be empty!")
        Assert.hasText(command.description, "Product description must not be empty!")

        this.productInformation = ProductInformation(
            name = command.name,
            description = command.description
        )

        raise(MasterDataUpdatedEvent(productNumber = id))
        log.info("Product master data updated. [productNumber={}]", id)
    }

    fun handle(command: UpdateMediaDataCommand) {

        Assert.hasText(command.imageUrl, "Image URL must not be empty!")

        this.imageUrl = command.imageUrl

        raise(MediaDataUpdatedEvent(productNumber = id))
        log.info("Product media data updated. [productNumber={}]", id)
    }
}