package de.bringmeister.connect.product.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class ProductTest {

    @Test
    fun `should create new product`() {

        val command = aCreateNewProductCommand()
        val product = Product(command)

        assertThat(product.productNumber.stringValue()).isEqualTo("12345")
        assertThat(product.productInformation.name).isEqualTo("Coca Cola")
        assertThat(product.productInformation.description).isEqualTo("This is a bottle of tasty Coca Cola!")
        assertThat(product.imageUrl).isNull()
    }

    @Test
    fun `should throw event when product is created`() {

        val command = aCreateNewProductCommand()
        val product = Product(command)

        val events = product.occurredEvents()
        assertThat(events).hasSize(1)
        assertThat(events[0]).isEqualTo(ProductCreatedEvent(productNumber = "12345"))
    }

    @Test
    fun `should clear events after returning them`() {

        val command = aCreateNewProductCommand()
        val product = Product(command)

        assertThat(product.occurredEvents()).hasSize(1)
        assertThat(product.occurredEvents()).hasSize(0) // list is empty now!
    }

    @Test
    fun `should throw exception on empty name`() {
        assertThatThrownBy({
            Product(CreateNewProductCommand(
                productNumber = "12345",
                name = "", // empty!
                description = "This is a bottle of tasty Coca Cola!"
            ))
        })
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasNoCause()
            .hasMessageContaining("Product name must not be empty!")
    }

    @Test
    fun `should throw exception on empty description`() {
        assertThatThrownBy({
            Product(CreateNewProductCommand(
                productNumber = "12345",
                name = "Coca Cola",
                description = "" // empty!
            ))
        })
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasNoCause()
            .hasMessageContaining("Product description must not be empty!")
    }

    @Test
    fun `should equal product with same ID`() {

        // We've got two products with different values but with
        // the same ID - so they represent the same product!

        val product1 = Product(CreateNewProductCommand(
            productNumber = "12345",
            name = "Coca Cola",
            description = "This is a bottle of tasty Coca Cola!"
        ))

        val product2 = Product(CreateNewProductCommand(
            productNumber = "12345",
            name = "Coca Cola",
            description = "This is a bottle of tasty Coca Cola!"
        ))

        assertThat(product1).isEqualTo(product2)
    }

    private fun aCreateNewProductCommand(): CreateNewProductCommand {
        return CreateNewProductCommand(
            productNumber = "12345",
            name = "Coca Cola",
            description = "This is a bottle of tasty Coca Cola!"
        )
    }
}