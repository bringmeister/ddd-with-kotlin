package de.bringmeister.connect.product.domain

interface CommandBus {
    fun send(command: Command)

    fun sendAll(commands: List<Command>) {
        commands.forEach(this::send)
    }
}
