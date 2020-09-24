package com.a.p.mvvm.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.Fragment
import com.a.p.main.mvvm.viewmodel.BaseViewModel


class TabViewModel(application: Application) :
    BaseViewModel<List<TabViewModel.DataModel>>(application) {
    var dataLive = MutableLiveData<Boolean>()

    data class DataModel(val title: String, val fragment: Fragment)

    var id = 0L

}
