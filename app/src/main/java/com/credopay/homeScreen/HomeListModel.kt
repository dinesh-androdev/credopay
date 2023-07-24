package com.credopay.homeScreen

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_list_model_item")
data class HomeListModelItem(
	val firstName: String? = null,
	val lastName: String? = null,
	val image: String? = null,
	val imageUrl: String? = null,
	val fullName: String? = null,
	@PrimaryKey
	val id: Int? = null,
	val title: String? = null,
	val family: String? = null
)
