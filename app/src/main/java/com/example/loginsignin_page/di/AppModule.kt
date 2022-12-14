package com.example.loginsignin_page.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.loginsignin_page.db.UserDatabase
import com.example.loginsignin_page.other.Constants.KEY_DATE_OF_BIRTH
import com.example.loginsignin_page.other.Constants.KEY_FIRST_NAME
import com.example.loginsignin_page.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.loginsignin_page.other.Constants.KEY_LAST_NAME
import com.example.loginsignin_page.other.Constants.KEY_PASSWORD
import com.example.loginsignin_page.other.Constants.KEY_USER_NAME
import com.example.loginsignin_page.other.Constants.SHARED_PREFERENCES_NAME
import com.example.loginsignin_page.other.Constants.USER_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        USER_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(db: UserDatabase) = db.getUserDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideFirstName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_FIRST_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideLastName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_LAST_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideUserName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_USER_NAME, "") ?: ""

    @Singleton
    @Provides
    fun providePassword(sharedPref: SharedPreferences) = sharedPref.getString(KEY_PASSWORD, "") ?: ""

    @Singleton
    @Provides
    fun provideDateOfBirth(sharedPref: SharedPreferences) = sharedPref.getString(KEY_DATE_OF_BIRTH, "") ?: ""

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE, true)
}