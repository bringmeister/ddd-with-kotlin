package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.domain.DomainEvent

data class MasterDataUpdatedEvent(val productNumber: String) : DomainEvent
