package de.bringmeister.connect.product.application.cdn

import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.product.ProductNumber

data class UpdateCdnCommand(
    val productNumber: ProductNumber
) : Command
