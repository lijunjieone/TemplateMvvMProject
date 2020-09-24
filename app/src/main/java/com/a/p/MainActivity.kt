package com.a.p

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.a.p.mvvm.fragment.MainFragment
import com.a.p.mvvm.fragment.TabFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TabFragment.newInstance())
                .commitNow()
        }
    }
}
