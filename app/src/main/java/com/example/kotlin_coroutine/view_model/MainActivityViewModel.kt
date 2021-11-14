package com.example.kotlin_coroutine.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutine.model.RecyclerList
import com.example.kotlin_coroutine.service.RetrofitInstance
import com.example.kotlin_coroutine.service.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    lateinit var rcyclerListData: MutableLiveData<RecyclerList>

    init {
        rcyclerListData = MutableLiveData()
    }

    fun getRecyclerListObserver(): MutableLiveData<RecyclerList> {
        return rcyclerListData
    }

    // get data from API
    // this is coroutine scope
    fun getAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
            val response = retrofitInstance.getDataFromApi(query = "ny")

            rcyclerListData.postValue(response)
        }
    }
}