package de.bringmeister.connect.product.infrastructure.guava

import com.google.common.eventbus.EventBus
import com.google.common.eventbus.Subscribe
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.stereotype.Component

@Component
class SubscriberPostProcessor(private val eventBus: EventBus) : BeanPostProcessor {

    override fun postProcessBeforeInitialization(bean: Any?, beanName: String?): Any {
        return bean!! // nothing to do in this case
    }

    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {
        for (method in bean.javaClass.methods) {
            if (method.isAnnotationPresent(Subscribe::class.java)) {
                this.eventBus.register(bean)
                break
            }
        }
        return bean
    }
}