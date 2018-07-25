package de.bringmeister.connect.product.application.cdn

import de.bringmeister.connect.product.domain.CommandListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ImageCdnService() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @CommandListener
    fun handle(command: UpdateCdnCommand) {

        // Here would be the place for some business logic
        // interacting with the external image CDN.

        log.info("Image CDN has been updated. [productNumber={}]", command.productNumber)
    }
}
