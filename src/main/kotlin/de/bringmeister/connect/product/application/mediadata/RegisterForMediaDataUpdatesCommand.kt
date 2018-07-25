package de.bringmeister.connect.product.application.mediadata

import de.bringmeister.connect.product.domain.Command

data class RegisterForMediaDataUpdatesCommand(val productNumber: String) : Command
