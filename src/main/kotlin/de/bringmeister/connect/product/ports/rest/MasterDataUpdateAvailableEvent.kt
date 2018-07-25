package de.bringmeister.connect.product.ports.rest

import de.bringmeister.connect.product.domain.Event
import de.bringmeister.connect.product.domain.product.ProductNumber

data class MasterDataUpdateAvailableEvent(
    val productNumber: ProductNumber,
    val name: String,
    val description: String
) : Event
