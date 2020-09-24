package com.a.p.mvvm.fragment

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ScrollView
import com.a.p.R


open class BaseFragment : Fragment() {

    protected lateinit var rootView: FrameLayout
    protected lateinit var contentView: View
    protected var stoneView: ViewGroup? = null
    protected var loadingView: ViewGroup? = null


    private var loadingType: Boolean = true //true为骨架屏，false为普通loading


    companion object {

        const val SHIMMER_DURATION = 1800L

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
//        rootView = inflater.inflate(R.layout.fragment_base, container, false) as FrameLayout

        rootView = FrameLayout(container!!.context)
        if (getContentId() != 0) {
            contentView = inflater.inflate(getContentId(), rootView, false)
            rootView.addView(contentView)
        } else {
            contentView = FrameLayout(container!!.context)
            rootView.addView(contentView)
        }

        if (getLoadingId() != 0) {
            loadingView = inflater.inflate(getLoadingId(), rootView, false) as ViewGroup
            rootView.addView(loadingView)

            loadingType = false
        }


        loadingView?.visibility = View.GONE
        stoneView?.visibility = View.GONE

        return rootView
    }



    /**
     * 设置加载的页面资源id
     */
    open fun getContentId(): Int {
        return 0
    }

    /**
     * 设置全屏loading的资源id
     */
    open fun getLoadingId(): Int {
        return 0
    }


}