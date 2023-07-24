package com.credopay.homeScreen

import com.credopay.network.AppApiService
import com.credopay.network.Result
import com.credopay.network.TaskCallback
import com.credopay.network.awaitResult
import com.credopay.roomDb.HomeRoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HomeScreenRepository(
    private val homeScreenService: AppApiService,
    private val roomDb: HomeRoomDb
) {
    private val completedJob = Job()
    private val backgroundScope = CoroutineScope(Dispatchers.IO + completedJob)
    private val foregroundScope = CoroutineScope(Dispatchers.Main)

    fun getAllCharacters(
        isInternetAvailable: Boolean,
        taskCallback: TaskCallback<List<HomeListModelItem>>
    ) {

        backgroundScope.launch {
            if (isInternetAvailable) {
                when (val result: Result<List<HomeListModelItem>> =

                    homeScreenService.getAllCharacters().awaitResult()) {

                    is Result.Ok -> {
                        insertData(result.value)
                        foregroundScope.launch { taskCallback.onComplete(result.value) }
                    }

                    is Result.Error -> foregroundScope.launch { taskCallback.onException(result.exception) }

                    is Result.Exception -> foregroundScope.launch { taskCallback.onException(result.exception) }
                }
            } else {
                taskCallback.onComplete(roomDb.wordDao().getAllItems())
            }

        }
    }

    private suspend fun insertData(list: List<HomeListModelItem>) {
        roomDb.wordDao().insert(list)
    }

}
