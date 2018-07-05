package de.bringmeister.connect.product.application.mediadata

import de.bringmeister.connect.product.domain.CommandListener
import org.springframework.stereotype.Service

@Service
class MediaDataService(private val mediaDataRegistry: MediaDataRegistry) {

    @CommandListener
    fun handle(command: RegisterForMediaDataUpdatesCommand) {
        mediaDataRegistry.handle(command)
    }
}