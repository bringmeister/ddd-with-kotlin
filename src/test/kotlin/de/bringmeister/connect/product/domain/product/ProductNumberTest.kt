package de.bringmeister.connect.product.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class ProductNumberTest {

    @Test
    fun `should create new product number`() {
        val productNumber = ProductNumber("3425435")
        assertThat(productNumber.stringValue()).isEqualTo("3425435")
    }

    @Test
    fun `should be equal to product number with same value`() {
        val productNumber1 = ProductNumber("3425435")
        val productNumber2 = ProductNumber("3425435")
        assertThat(productNumber1).isEqualTo(productNumber2)
    }

    @Test
    fun `should not be equal to product number with different value`() {
        val productNumber1 = ProductNumber("34254351")
        val productNumber2 = ProductNumber("34254352")
        assertThat(productNumber1).isNotEqualTo(productNumber2)
    }

    @Test
    fun `should use simple toString format`() {
        val productNumber = ProductNumber("3425435")
        assertThat(productNumber.toString()).isEqualTo("3425435")
    }

    @Test
    fun `should throw exception on invalid format`() {
        assertThatThrownBy({ ProductNumber("A3425435") })
            .isInstanceOf(IllegalStateException::class.java)
            .hasNoCause()
            .hasMessageContaining("Product number has an invalid format: A3425435")
    }
}