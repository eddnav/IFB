package com.eddnav.ifb

import android.app.Application
import android.arch.persistence.room.Room
import com.eddnav.ifb.cache.IFBDatabase
import com.jakewharton.threetenabp.AndroidThreeTen
import com.thedeadpixelsociety.passport.Passport
import com.thedeadpixelsociety.passport.TextInputLayoutValidator

/**
 * @author Eduardo Naveda
 */
class IFBApp : Application() {

    var database: IFBDatabase = Room.databaseBuilder(this, IFBDatabase::class.java, "ifb-db").build()

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        Passport.validatorFactory({ TextInputLayoutValidator() })
    }
}