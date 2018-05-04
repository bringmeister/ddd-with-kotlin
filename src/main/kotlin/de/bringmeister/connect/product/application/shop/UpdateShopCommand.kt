package de.bringmeister.connect.product.application.shop

import de.bringmeister.connect.product.domain.Command

data class UpdateShopCommand(val productNumber: String): Command