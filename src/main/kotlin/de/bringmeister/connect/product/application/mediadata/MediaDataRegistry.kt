package de.bringmeister.connect.product.application.mediadata

interface MediaDataRegistry {
    fun handle(command: RegisterForMediaDataUpdatesCommand)
}