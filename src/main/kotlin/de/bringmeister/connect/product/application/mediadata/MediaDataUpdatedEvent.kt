package de.bringmeister.connect.product.application.mediadata

import de.bringmeister.connect.product.domain.DomainEvent

data class MediaDataUpdatedEvent(val productNumber: String) : DomainEvent
