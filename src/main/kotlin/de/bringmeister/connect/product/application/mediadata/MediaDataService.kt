package de.bringmeister.connect.product.application.mediadata

import com.google.common.eventbus.Subscribe
import org.springframework.stereotype.Service

@Service
class MediaDataService(private val mediaDataRegistry: MediaDataRegistry) {

    @Subscribe
    fun handle(command: RegisterForMediaDataUpdatesCommand) {
        mediaDataRegistry.handle(command)
    }
}