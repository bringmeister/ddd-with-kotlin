package de.bringmeister.connect.product.ports.rest

import de.bringmeister.connect.product.domain.DomainEventBus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

/**
 * A REST controller to simulate the two starting points of the demo application:
 *
 *  - The "MasterDataUpdateAvailableEvent" is thrown by the "Master Data Service".
 *  - The "MediaDataUpdateAvailableEvent" is thrown by the "Media Data Service".
 *
 * See the "README.md" for an overview of the business process!
 */
@RestController
class DemoController(private val domainEventBus: DomainEventBus) {

    @PostMapping("/master_data_update")
    fun masterDataUpdate() {

        // Simulate an incoming event!

        domainEventBus.send(MasterDataUpdateAvailableEvent(
            articleNumber = "345322523",
            name = "Coca Cola",
            description = "A bottle of tasty Coca Cola"
        ))
    }

    @PostMapping("/media_data_update")
    fun mediaDataUpdate() {

        // Simulate an incoming event!

        domainEventBus.send(MediaDataUpdateAvailableEvent(
            productNumber = "345322523",
            imageUrl = "www.my-domain.com/my-image.jpg"
        ))
    }


}
