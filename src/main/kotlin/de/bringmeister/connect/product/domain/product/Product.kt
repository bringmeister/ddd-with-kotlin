package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.application.mediadata.MediaDataUpdatedEvent
import de.bringmeister.connect.product.domain.DomainEvent
import de.bringmeister.connect.product.domain.DomainEventHolder
import org.apache.commons.lang3.builder.EqualsBuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * The product domain entity.
 */
class Product {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    private val domainEventHolder: DomainEventHolder = DomainEventHolder()

    val productNumber: ProductNumber

    private var productInformation: ProductInformation
    private var imageUrl: String? = null // we  have none until we get the first media data update

    constructor(command: CreatNewProductCommand) {

        productNumber = ProductNumber(command.productNumber)

        productInformation = ProductInformation(
            name = command.name,
            description = command.description
        )

        domainEventHolder.raise(ProductCreatedEvent(
            productNumber = productNumber.productNumber
        ))
        log.info("New product created. [productNumber={}]", productNumber)
    }

    fun occuredEvents(): List<DomainEvent> {
        return domainEventHolder.occurredEvents()
    }

    fun updateMasterData(command: UpdateMasterDataCommand) {

        // Some logic...

        domainEventHolder.raise(MasterDataUpdatedEvent(
            productNumber = productNumber.productNumber
        ))
        log.info("Product master data updated. [productNumber={}]", productNumber)
    }

    fun updateMediaData(command: UpdateMediaDataCommand) {

        // Some logic...

        this.imageUrl = command.imageUrl

        domainEventHolder.raise(MediaDataUpdatedEvent(
            productNumber = productNumber.productNumber
        ))
        log.info("Product media data updated. [productNumber={}]", productNumber)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Product) {
            return false
        }
        return EqualsBuilder()
                        .append(this.productNumber, other.productNumber) // Only on the ID!
                        .isEquals
    }

    override fun hashCode(): Int {
        return productNumber.hashCode() // Only on the ID!
    }
}