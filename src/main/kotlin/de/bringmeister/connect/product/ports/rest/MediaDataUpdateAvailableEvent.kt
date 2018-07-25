package de.bringmeister.connect.product.ports.rest

import de.bringmeister.connect.product.domain.Event
import de.bringmeister.connect.product.domain.product.ProductNumber

data class MediaDataUpdateAvailableEvent(
    val productNumber: ProductNumber,
    val imageUrl: String
) : Event
