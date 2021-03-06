package com.example.myapplication.ui.roomdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ProductRepository(private val productDao: ProductDao) {

    val allProducts: LiveData<List<Product>> = productDao.getAllProducts()
    val searchResults = MutableLiveData<List<Product>>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertProduct(newProduct: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.insertProduct(newProduct)
        }
    }

    fun findProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.findProduct(name)
        }
    }

    fun deleteProduct(name: String) {
        coroutineScope.launch(Dispatchers.IO) {
            productDao.deleteProduct(name)
        }
    }

    private fun asyncFind(name: String): Deferred<List<Product>?> =
        coroutineScope.async(Dispatchers.IO) { return@async productDao.findProduct(name) }
}