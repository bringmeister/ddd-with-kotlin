package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.domain.Event

data class MasterDataUpdatedEvent(
    val productNumber: ProductNumber
) : Event
