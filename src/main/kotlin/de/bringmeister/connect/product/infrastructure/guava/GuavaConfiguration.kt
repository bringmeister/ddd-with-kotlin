package de.bringmeister.connect.product.infrastructure.guava

import com.google.common.eventbus.AsyncEventBus
import com.google.common.eventbus.EventBus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors

@Configuration
class GuavaConfiguration {

    @Bean
    fun eventBus(): EventBus {
        return AsyncEventBus(Executors.newFixedThreadPool(10))
    }
}