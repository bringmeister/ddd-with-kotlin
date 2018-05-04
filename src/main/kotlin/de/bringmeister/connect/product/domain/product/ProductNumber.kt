package de.bringmeister.connect.product.domain.product

import org.springframework.util.Assert

data class ProductNumber(val productNumber: String) {

    private val justNumbersRegex = Regex("[0-9]+")

    init {
        Assert.state(productNumber.matches(justNumbersRegex), "Product number has an invalid format: ${productNumber}")
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
