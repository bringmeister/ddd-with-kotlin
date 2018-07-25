package de.bringmeister.connect.product.ports.rest

import de.bringmeister.connect.product.domain.DomainEvent

data class MediaDataUpdateAvailableEvent(
    val productNumber: String,
    val imageUrl: String
) : DomainEvent
