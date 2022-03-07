package com.example.restaurantreview

open class Event<out T>(private val content : T) {

    @Suppress("MemberVisibilityCanBePrivate")
    var hasBeenHandled = false
        private set

    /**
     * [Main focus of this class is here :D ]
     * getContentIfNotHandled(). Fungsi tersebut akan memeriksa apakah aksi ini
     * pernah dieksekusi sebelumnya. Caranya yaitu dengan memanipulasi
     * variabel hasBeenHandled.
     * */
    fun getContentIfNotHandled(): T? {
        return if(hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content

}