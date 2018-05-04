package de.bringmeister.connect.product.ports.rest

import de.bringmeister.connect.product.domain.DomainEventBus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DomainEventController(private val domainEventBus: DomainEventBus) {

    @PostMapping("/master_data_update")
    fun masterDataUpdate() {
        domainEventBus.send(MasterDataUpdateAvailableEvent(
            articleNumber = "345322523",
            name = "Coca Cola",
            description = "A bottle of tasty Coca Cola"
        ))
    }

    @PostMapping("/media_data_update")
    fun mediaDataUpdate() {
        domainEventBus.send(MediaDataUpdateAvailableEvent(
            productNumber = "345322523",
            imageUrl = "www.my-domain.com/my-image.jpg"
        ))
    }


}
