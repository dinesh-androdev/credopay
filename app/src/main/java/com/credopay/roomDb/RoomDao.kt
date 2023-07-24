package com.credopay.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.credopay.homeScreen.HomeListModelItem

@Dao
interface RoomDao{

    @Query("SELECT * FROM home_list_model_item")
    fun getAllItems(): List<HomeListModelItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: List<HomeListModelItem>)
}
