package com.eddnav.ifb.cache

/**
 * @author Eduardo Naveda
 */
interface Representation<out T> {

    fun toDomain(): T
}