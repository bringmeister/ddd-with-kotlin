package de.bringmeister.connect.product.domain.product

interface ProductRepository {
    fun find(productNumber: ProductNumber): Product
    fun exists(productNumber: ProductNumber): Boolean
    fun save(product: Product)
}