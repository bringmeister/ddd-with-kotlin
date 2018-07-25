package de.bringmeister.connect.product.application.cdn

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.Appender
import de.bringmeister.connect.product.domain.product.ProductNumber
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.slf4j.LoggerFactory.getLogger

@RunWith(MockitoJUnitRunner::class)
class ImageCdnServiceTest {

    @Mock
    private lateinit var appender: Appender<ILoggingEvent>

    @Captor
    private lateinit var captor: ArgumentCaptor<ILoggingEvent>

    @Before
    fun setUp() {
        val logger = getLogger(ROOT_LOGGER_NAME) as Logger
        logger.addAppender(appender)
    }

    @After
    fun tearDown() {
        val logger = getLogger(ROOT_LOGGER_NAME) as Logger
        logger.detachAppender(appender)
    }

    @Test
    fun `should update Image CDN`() {

        val command = UpdateCdnCommand(ProductNumber("P-000001"))
        val service = ImageCdnService()
        service.handle(command)

        verify(appender).doAppend(captor.capture())
        val loggingEvent = captor.value
        assertEquals(loggingEvent.formattedMessage, "Image CDN has been updated. [productNumber=P-000001]");
    }
}