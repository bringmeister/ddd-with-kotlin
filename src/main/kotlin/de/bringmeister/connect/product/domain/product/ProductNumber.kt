package de.bringmeister.connect.product.domain.product

import org.springframework.util.Assert

data class ProductNumber(val productNumber: String) {

    private val productNumberFormat = Regex("P-[0-9]{6}")

    init {
        Assert.state(
            productNumber.matches(productNumberFormat),
            "Product number has an invalid format: ${productNumber}"
        )
    }

    fun stringValue(): String {
        return productNumber
    }

    // We want a simpler toString format so that we can
    // use this object in log statements more easily.
    override fun toString(): String {
        return productNumber
    }
}
