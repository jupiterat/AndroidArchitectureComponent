package com.research.aac

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.research.aac.infra.db.dao.DemoDatabase

class DemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}