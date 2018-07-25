package de.bringmeister.connect.product.application.search

import de.bringmeister.connect.product.domain.Command

data class UpdateSearchIndexCommand(val productNumber: String) : Command