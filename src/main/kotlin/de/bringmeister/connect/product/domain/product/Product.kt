package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.application.mediadata.MediaDataUpdatedEvent
import de.bringmeister.connect.product.domain.DomainEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Product {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    private val occurredEvents: MutableList<DomainEvent> = mutableListOf()

    val productNumber: ProductNumber

    private var productInformation: ProductInformation
    private var imageUrl: String? = null // we  have none until we get the first media data update

    constructor(command: CreatNewProductCommand) {

        productNumber = ProductNumber(command.productNumber)

        productInformation = ProductInformation(
            name = command.name,
            description = command.description
        )

        occurredEvents.add(ProductCreatedEvent(
            productNumber = productNumber.productNumber
        ))
        log.info("New product created. [productNumber={}]", productNumber)
    }

    fun occuredEvents(): List<DomainEvent> {
        val events = this.occurredEvents.toMutableList()
        this.occurredEvents.clear()
        return events
    }

    fun updateMasterData(command: UpdateMasterDataCommand) {

        // Some logic...

        occurredEvents.add(MasterDataUpdatedEvent(
            productNumber = productNumber.productNumber
        ))
        log.info("Product master data updated. [productNumber={}]", productNumber)
    }

    fun updateMediaData(command: UpdateMediaDataCommand) {

        // Some logic...

        this.imageUrl = command.imageUrl

        occurredEvents.add(MediaDataUpdatedEvent(
            productNumber = productNumber.productNumber
        ))
        log.info("Product media data updated. [productNumber={}]", productNumber)
    }
}