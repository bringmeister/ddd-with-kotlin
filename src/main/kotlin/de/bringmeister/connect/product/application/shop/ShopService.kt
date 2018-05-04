package de.bringmeister.connect.product.application.shop

import com.google.common.eventbus.Subscribe
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ShopService() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Subscribe
    fun handle(command: UpdateShopCommand) {
        Thread.sleep(2000)
        log.info("Shop has been updated. [productNumber={}]", command.productNumber)
    }
}