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
        Thread.sleep(4000)
        log.info("Image CDN has been updated. [productNumber={}]", command.productNumber)
    }
}