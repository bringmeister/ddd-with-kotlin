package de.bringmeister.connect.product.application.mediadata

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class MediaDataService(private val mediaDataRegistry: MediaDataRegistry) {

    @EventListener
    fun handle(command: RegisterForMediaDataUpdatesCommand) {
        mediaDataRegistry.handle(command)
    }
}