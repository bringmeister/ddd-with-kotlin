package de.bringmeister.connect.product.application.shop

import de.bringmeister.connect.product.domain.CommandListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ShopService() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @CommandListener
    fun handle(command: UpdateShopCommand) {

        // Here would be the place for some business logic
        // interacting with the external shop system.

        log.info("Shop has been updated. [productNumber={}]", command.productNumber)
    }
}