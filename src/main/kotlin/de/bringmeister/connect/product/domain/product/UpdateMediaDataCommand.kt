package de.bringmeister.connect.product.domain.product

import de.bringmeister.connect.product.domain.Command

data class UpdateMediaDataCommand(
    val productNumber: ProductNumber,
    val imageUrl: String
) : Command
