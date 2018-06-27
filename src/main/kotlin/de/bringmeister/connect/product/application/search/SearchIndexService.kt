package de.bringmeister.connect.product.application.search

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class SearchIndexService() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @EventListener
    fun handle(command: UpdateSearchIndexCommand) {
        Thread.sleep(1000)
        log.info("Search index has been updated. [productNumber={}]", command.productNumber)
    }
}