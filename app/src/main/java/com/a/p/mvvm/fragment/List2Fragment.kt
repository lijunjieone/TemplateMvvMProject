package com.a.p.mvvm.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.a.p.R
import com.a.p.databinding.FragmentList2Binding
import com.a.p.mvvm.viewmodel.List2ViewModel
import com.a.p.views.adapter.List2Adapter
import android.os.Handler
import android.support.v7.widget.SimpleItemAnimator
import android.support.v4.app.Fragment

import com.a.p.views.CommonMultiItem


class List2Fragment : BaseFragment() {

    protected lateinit var binding: FragmentList2Binding
    lateinit var viewModel: List2ViewModel
    private lateinit var adapter: List2Adapter
    var id: Long = 0L


    override fun getContentId(): Int {
        return R.layout.fragment_list2
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getContentId(), container, false)
        initViewModel()
        initView()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initData()
    }


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(List2ViewModel::class.java)
        //可以在这里完成外部数据跟viewmodel的通讯
        id = arguments?.getLong(PARAM_DEFAULT_ID) ?: 0L
        viewModel.id = id
    }


    private fun initObserver() {
        viewModel.itemList.observe(this, Observer {
            it?.let {
                val items = it.map {
                    CommonMultiItem<List2ViewModel.DataModel>(
                        if (it.letter.startsWith("Item: 1")) CommonMultiItem.ITEM_HEADER else CommonMultiItem.ITEM_ONE,
                        0,
                        it
                    )
                }
                adapter.replaceData(items)

            }
        })
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

    }

    private fun initView() {
        adapter = List2Adapter(ArrayList<CommonMultiItem<List2ViewModel.DataModel>>())
        binding.recyclerView.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //解决刷新闪烁问题
        val animator = binding.recyclerView.itemAnimator as SimpleItemAnimator
        animator.supportsChangeAnimations = false


    }

    private fun initData() {
        viewModel.loadData()
    }


    companion object {
        const val PARAM_DEFAULT_ID = "param_default_id"

        fun newInstance(id: Long = 0L): Fragment = List2Fragment().apply {
            //在这里完成Fragment和外部数据的通讯
            val args = Bundle()
            args.putLong(PARAM_DEFAULT_ID, id)
            arguments = args
        }
    }


}

