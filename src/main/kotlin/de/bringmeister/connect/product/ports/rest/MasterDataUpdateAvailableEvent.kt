package de.bringmeister.connect.product.ports.rest

import de.bringmeister.connect.product.domain.DomainEvent

data class MasterDataUpdateAvailableEvent(val articleNumber: String,
                                          val name: String,
                                          val description: String) : DomainEvent