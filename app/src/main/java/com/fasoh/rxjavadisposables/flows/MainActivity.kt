package com.fasoh.rxjavadisposables.flows

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.fasoh.rxjavadisposables.RxDisposer
import com.fasoh.rxjavadisposables.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        viewModel.setup(RxDisposer().apply { bind(lifecycle) })
        viewModel.rates.observe(this, Observer {
            //If its 1 you exceeded the requests per hour.
            if (it.size > 1)
                recycler.adapter = RateAdapter(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}