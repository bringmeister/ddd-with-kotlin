package de.bringmeister.connect.product.domain.product

data class ProductNumber(val productNumber: String) {

    override fun toString(): String {
        return productNumber
    }
}
