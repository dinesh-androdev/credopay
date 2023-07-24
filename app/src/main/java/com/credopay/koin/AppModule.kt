package com.credopay.koin

import androidx.room.Room
import com.credopay.homeScreen.HomeScreenRepository
import com.credopay.homeScreen.HomeScreenViewModel
import com.credopay.network.AppApiService
import com.credopay.roomDb.HomeRoomDb
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://thronesapi.com/api/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AppApiService::class.java)
    }
    single {
        Room.databaseBuilder(
            androidApplication(),
            HomeRoomDb::class.java, "home_database"
        ).build()
    }
    single {
        HomeScreenRepository(get(),get())
    }
    viewModel {
        HomeScreenViewModel(get())
    }
}