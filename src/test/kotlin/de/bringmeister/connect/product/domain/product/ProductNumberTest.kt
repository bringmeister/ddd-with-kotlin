package de.bringmeister.connect.product.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class ProductNumberTest {

    @Test
    fun `should create new product number`() {
        val productNumber = ProductNumber("P-000001")
        assertThat(productNumber.stringValue()).isEqualTo("P-000001")
    }

    @Test
    fun `should be equal to product number with same value`() {
        val productNumber1 = ProductNumber("P-000001")
        val productNumber2 = ProductNumber("P-000001")
        assertThat(productNumber1).isEqualTo(productNumber2)
    }

    @Test
    fun `should not be equal to product number with different value`() {
        val productNumber1 = ProductNumber("P-000001")
        val productNumber2 = ProductNumber("P-000002")
        assertThat(productNumber1).isNotEqualTo(productNumber2)
    }

    @Test
    fun `should use simple toString format`() {
        val productNumber = ProductNumber("P-000001")
        assertThat(productNumber.toString()).isEqualTo("P-000001")
    }

    @Test
    fun `should throw exception on invalid format`() {
        assertThatThrownBy { ProductNumber("X-1") }
            .isInstanceOf(IllegalStateException::class.java)
            .hasNoCause()
            .hasMessageContaining("Product number has an invalid format: X-1")
    }
}