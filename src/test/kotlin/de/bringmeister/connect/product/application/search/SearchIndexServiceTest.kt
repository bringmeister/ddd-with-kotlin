package de.bringmeister.connect.product.application.search

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.Appender
import de.bringmeister.connect.product.domain.product.ProductNumber
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.slf4j.LoggerFactory

@RunWith(MockitoJUnitRunner::class)
class SearchIndexServiceTest {

    @Mock
    private lateinit var appender: Appender<ILoggingEvent>

    @Captor
    private lateinit var captor: ArgumentCaptor<ILoggingEvent>

    @Before
    fun setUp() {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.addAppender(appender)
    }

    @After
    fun tearDown() {
        val logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        logger.detachAppender(appender)
    }

    @Test
    fun `should update Image CDN`() {

        val command = UpdateSearchIndexCommand(ProductNumber("P-000001"))
        val service = SearchIndexService()
        service.handle(command)

        Mockito.verify(appender).doAppend(captor.capture())
        val loggingEvent = captor.value
        Assert.assertEquals(loggingEvent.formattedMessage, "Search index has been updated. [productNumber=P-000001]");
    }
}