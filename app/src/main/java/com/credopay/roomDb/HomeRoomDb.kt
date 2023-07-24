package com.credopay.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.credopay.homeScreen.HomeListModelItem

@Database(entities = [HomeListModelItem::class], version = 1, exportSchema = false)
abstract class HomeRoomDb : RoomDatabase() {
    abstract fun wordDao(): RoomDao
}