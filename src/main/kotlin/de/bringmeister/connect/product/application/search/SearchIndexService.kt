package de.bringmeister.connect.product.application.search

import de.bringmeister.connect.product.domain.CommandListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SearchIndexService() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @CommandListener
    fun handle(command: UpdateSearchIndexCommand) {

        // Here would be the place for some business logic
        // interacting with the external search index.

        log.info("Search index has been updated. [productNumber={}]", command.productNumber)
    }
}