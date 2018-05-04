package de.bringmeister.connect.product.application.cdn

import de.bringmeister.connect.product.domain.Command

data class UpdateCdnCommand(val productNumber: String): Command