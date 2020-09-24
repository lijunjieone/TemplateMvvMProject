package com.a.p.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.a.p.main.mvvm.viewmodel.BaseViewModel

class List2ViewModel(application: Application) :
    BaseViewModel<List<List2ViewModel.DataModel>>(application) {

    var dataLive = MutableLiveData<Boolean>()
    var loading = false
    var id = 0L

    init {
        canReload = true
        canLoadMore = false
    }

    data class DataModel(val letter: String, val position: Int)


    override fun loadData(): LiveData<List<List2ViewModel.DataModel>> {
        if (loading) {
            return itemList
        }
        val list = ArrayList<List2ViewModel.DataModel>()
        val size = itemList.value?.size ?: 1

        if (canReload) {
            for (i in 0..50) {
                val model = List2ViewModel.DataModel("Item: ${i}", i)
                list.add(model)
            }
        } else if (canLoadMore) {
            for (i in size..size + 50) {
                val model = List2ViewModel.DataModel("Item: ${i}", i)
                list.add(model)
            }
        }
        canLoadMore = size <= 150
        //itemList.value = handleListRequestResponse(list=list,haveMore = canLoadMore)
        itemList.value = list
        return itemList
    }


}