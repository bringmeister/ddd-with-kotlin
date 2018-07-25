package de.bringmeister.connect.product.application.mediadata

import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.product.ProductNumber

data class RegisterForMediaDataUpdatesCommand(
    val productNumber: ProductNumber
) : Command
