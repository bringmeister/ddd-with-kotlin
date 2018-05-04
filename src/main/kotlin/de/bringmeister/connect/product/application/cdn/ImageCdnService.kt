package de.bringmeister.connect.product.application.cdn

import com.google.common.eventbus.Subscribe
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ImageCdnService() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Subscribe
    fun handle(command: UpdateCdnCommand) {
        Thread.sleep(4000)
        log.info("Image CDN has been updated. [productNumber={}]", command.productNumber)
    }
}