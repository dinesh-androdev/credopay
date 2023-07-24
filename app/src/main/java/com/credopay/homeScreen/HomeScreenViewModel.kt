package com.credopay.homeScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.credopay.Util
import com.credopay.network.TaskCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeScreenViewModel(private val homeScreenRepository: HomeScreenRepository) : ViewModel() {

    private val _noDataFound = MutableStateFlow<String?>(null)
    val noDataFound: StateFlow<String?> = _noDataFound

    private val _signInStatus = MutableStateFlow<List<HomeListModelItem>?>(null)
    val signInStatus: StateFlow<List<HomeListModelItem>?> = _signInStatus

    val recyclerViewAdapter: HomeListAdapter = HomeListAdapter()

    private fun setAdapterData(list: List<HomeListModelItem>) {
        recyclerViewAdapter.setDataList(list)
    }

    fun getAllCharacters(context: Context) {
        homeScreenRepository.getAllCharacters(Util.isNetworkAvailable(context),
            object : TaskCallback<List<HomeListModelItem>> {
                override fun onComplete(result: List<HomeListModelItem>?) {
                    if (result.isNullOrEmpty()) {
                        _noDataFound.value = "No data found"
                    } else {
                        _noDataFound.value = ""
                        _signInStatus.value = result
                        setAdapterData(result)
                    }
                }

                override fun onException(t: Throwable?) {
                    _noDataFound.value = "No data found"
                }
            })
    }
}
