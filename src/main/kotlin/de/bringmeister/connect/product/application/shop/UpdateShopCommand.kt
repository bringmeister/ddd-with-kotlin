package de.bringmeister.connect.product.application.shop

import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.product.ProductNumber

data class UpdateShopCommand(
    val productNumber: ProductNumber
) : Command
