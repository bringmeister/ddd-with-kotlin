package de.bringmeister.connect.product.infrastructure.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.support.TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER

@Configuration
class SpringApplicationEventConfig {

    /**
     * We want our event/command handling to be asynchronous. By default,
     * Spring will handle application events synchronous. The following
     * bean will make the event/command handling asynchronous.
     *
     * See: http://zoltanaltfatter.com/2016/05/11/application-events-with-spring
     */
    @Bean
    fun applicationEventMulticaster(): ApplicationEventMulticaster {
        return SimpleApplicationEventMulticaster().apply {
            setTaskExecutor(SimpleAsyncTaskExecutor())
            setErrorHandler(LOG_AND_SUPPRESS_ERROR_HANDLER)
        }
    }
}
