package de.bringmeister.connect.product.infrastructure.guava

import com.google.common.eventbus.AsyncEventBus
import de.bringmeister.connect.product.domain.Command
import de.bringmeister.connect.product.domain.CommandBus
import org.springframework.stereotype.Component

@Component
class GuavaCommandBus(private val bus: AsyncEventBus): CommandBus {

    override fun send(command: Command) {
        bus.post(command)
    }
}
