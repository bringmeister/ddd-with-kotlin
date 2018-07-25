package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.domain.Event

data class MediaDataUpdatedEvent(
    val productNumber: ProductNumber
) : Event
