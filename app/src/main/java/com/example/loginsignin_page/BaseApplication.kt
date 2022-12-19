package com.example.loginsignin_page

import android.app.Application
import com.example.loginsignin_page.db.UserDatabase

class BaseApplication: Application() {
    val db: UserDatabase by lazy { UserDatabase.getDatabase(this) }
}