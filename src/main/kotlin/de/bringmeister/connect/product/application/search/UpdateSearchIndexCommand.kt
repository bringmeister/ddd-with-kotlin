package de.bringmeister.connect.product.application.search

import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.product.ProductNumber

data class UpdateSearchIndexCommand(
    val productNumber: ProductNumber
) : Command
