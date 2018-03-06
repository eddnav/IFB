package com.eddnav.ifb

import android.app.Application
import android.arch.persistence.room.Room
import com.eddnav.ifb.cache.IFBDatabase

/**
 * @author Eduardo Naveda
 */
class IFBApp : Application() {

    var database: IFBDatabase = Room.databaseBuilder(this, IFBDatabase::class.java, "ifb-db").allowMainThreadQueries().build()

}