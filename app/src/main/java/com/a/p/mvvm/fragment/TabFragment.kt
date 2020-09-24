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
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.a.p.R
import com.a.p.databinding.FragmentTabBinding
import com.a.p.mvvm.viewmodel.TabViewModel


class TabFragment : BaseFragment() {

    protected lateinit var binding: FragmentTabBinding
    lateinit var viewModel: TabViewModel
    private lateinit var adapter: TabAdapter
    var id: Long = 0L


    private val fragments = ArrayList<TabViewModel.DataModel>()

    override fun getContentId(): Int {
        return R.layout.fragment_tab
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
        viewModel = ViewModelProviders.of(this).get(TabViewModel::class.java)
        //可以在这里完成外部数据跟viewmodel的通讯
        id = arguments?.getLong(PARAM_DEFAULT_ID) ?: 0L
        viewModel.id = id

    }


    private fun initObserver() {
    }


    private fun initView() {
        initFragments()
        binding.viewPager.adapter = TabAdapter(childFragmentManager)
        binding.viewPager.offscreenPageLimit = 3
        binding.tabLayout.setViewPager(binding.viewPager)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initData() {
    }

    private fun initFragments() {
        fragments.add(TabViewModel.DataModel("TAB1", EmptyFragment()))
        fragments.add(TabViewModel.DataModel("TAB2", EmptyFragment()))
        fragments.add(TabViewModel.DataModel("TAB3", EmptyFragment()))
    }

    inner class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(p0: Int): Fragment {
            return fragments[p0].fragment
        }

        override fun getCount() = fragments.size

        override fun getPageTitle(position: Int): CharSequence? {
            return fragments[position].title
        }
    }


    companion object {
        const val PARAM_DEFAULT_ID = "param_default_id"

        fun newInstance(id: Long = 0L): Fragment = TabFragment().apply {
            //在这里完成Fragment和外部数据的通讯
            val args = Bundle()
            args.putLong(PARAM_DEFAULT_ID, id)
            arguments = args
        }
    }


}
