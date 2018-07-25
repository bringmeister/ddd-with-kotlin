package de.bringmeister.connect.product

import de.bringmeister.connect.product.AcceptanceTest.RecordingHandler
import de.bringmeister.connect.product.application.cdn.UpdateCdnCommand
import de.bringmeister.connect.product.application.mediadata.RegisterForMediaDataUpdatesCommand
import de.bringmeister.connect.product.application.search.UpdateSearchIndexCommand
import de.bringmeister.connect.product.application.shop.UpdateShopCommand
import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.CommandListener
import de.bringmeister.connect.product.domain.Event
import de.bringmeister.connect.product.domain.EventBus
import de.bringmeister.connect.product.domain.EventListener
import de.bringmeister.connect.product.domain.product.CreateNewProductCommand
import de.bringmeister.connect.product.domain.product.MasterDataUpdatedEvent
import de.bringmeister.connect.product.domain.product.MediaDataUpdatedEvent
import de.bringmeister.connect.product.domain.product.Product
import de.bringmeister.connect.product.domain.product.ProductCreatedEvent
import de.bringmeister.connect.product.domain.product.ProductNumber
import de.bringmeister.connect.product.domain.product.UpdateMasterDataCommand
import de.bringmeister.connect.product.domain.product.UpdateMediaDataCommand
import de.bringmeister.connect.product.infrastructure.stubs.StubbedProductRepository
import de.bringmeister.connect.product.ports.rest.MasterDataUpdateAvailableEvent
import de.bringmeister.connect.product.ports.rest.MediaDataUpdateAvailableEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Service
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS

@RunWith(SpringRunner::class)
@SpringBootTest
@Import(RecordingHandler::class)
class AcceptanceTest {

    private val productNumber = ProductNumber("P-000001")
    private val name = "Coca-Cola"
    private val description = "Tasty Coca-Cola!"
    private val url = "www.my-domain.com/my-new-image.jpg"

    @Autowired
    private lateinit var eventBus: EventBus

    @Autowired
    private lateinit var recordingHandler: RecordingHandler

    @Autowired
    private lateinit var productRepository: StubbedProductRepository

    companion object {
        var latch = CountDownLatch(1)
    }

    @Before
    fun setUp() {
        productRepository.clear()
        recordingHandler.clear()
    }

    @Test
    fun `should create new product when master data is updated for the first time`() {

        val input = MasterDataUpdateAvailableEvent(productNumber, name, description)

        val expectedMessages = listOf(
            input,
            CreateNewProductCommand(productNumber, name, description),
            ProductCreatedEvent(productNumber),
            RegisterForMediaDataUpdatesCommand(productNumber),
            UpdateShopCommand(productNumber),
            UpdateSearchIndexCommand(productNumber),
            MediaDataUpdateAvailableEvent(productNumber, url),
            UpdateMediaDataCommand(productNumber, url),
            MediaDataUpdatedEvent(productNumber),
            UpdateCdnCommand(productNumber)
        )

        eventBus.send(input)
        waitFor(expectedMessages)

        assertThat(recordingHandler.messages).isEqualTo(expectedMessages)
    }

    @Test
    fun `should update an existing product when master data is updated`() {

        prepareAnExistingProduct()

        val input = MasterDataUpdateAvailableEvent(productNumber, name, description)

        val expectedMessages = listOf(
            input,
            UpdateMasterDataCommand(productNumber, name, description),
            MasterDataUpdatedEvent(productNumber),
            UpdateShopCommand(productNumber),
            UpdateSearchIndexCommand(productNumber)
        )

        eventBus.send(input)
        waitFor(expectedMessages)

        assertThat(recordingHandler.messages).isEqualTo(expectedMessages)
    }

    @Test
    fun `should ignore media data updates for unknown products`() {

        val input = MediaDataUpdateAvailableEvent(productNumber, url)

        val expectedMessages = listOf(
            input,
            UpdateMediaDataCommand(productNumber, url)
        )

        eventBus.send(input)
        waitFor(expectedMessages)

        assertThat(recordingHandler.messages).isEqualTo(expectedMessages)
    }

    @Test
    fun `should apply media data updates for existing products`() {

        prepareAnExistingProduct()

        val input = MediaDataUpdateAvailableEvent(productNumber, url)

        val expectedMessages = listOf(
            input,
            UpdateMediaDataCommand(productNumber, url),
            MediaDataUpdatedEvent(productNumber),
            UpdateCdnCommand(productNumber)
        )

        eventBus.send(input)
        waitFor(expectedMessages)

        assertThat(recordingHandler.messages).isEqualTo(expectedMessages)
    }

    private fun waitFor(expectedEvents: List<Any>) {
        latch = CountDownLatch(expectedEvents.size)
        latch.await(10, SECONDS)
    }

    private fun prepareAnExistingProduct() {
        val command = CreateNewProductCommand(productNumber, name, description)
        val product = Product(command)
        product.occurredEvents() // get the events once to clear the list
        productRepository.save(product)
    }

    @Service
    class RecordingHandler {

        val messages = mutableListOf<Any>()

        @EventListener
        fun handle(event: Event) {
            messages.add(event)
            latch.countDown()
        }

        @CommandListener
        fun handle(command: Command) {
            messages.add(command)
            latch.countDown()
        }

        fun clear() {
            messages.clear()
        }
    }
}
