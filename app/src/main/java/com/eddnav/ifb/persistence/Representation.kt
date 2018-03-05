package com.eddnav.ifb.persistence

/**
 * @author Eduardo Naveda
 */
interface Representation<out T> {

    fun toDomain(): T

}