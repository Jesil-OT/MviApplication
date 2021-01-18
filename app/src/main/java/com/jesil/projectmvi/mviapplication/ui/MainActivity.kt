package com.jesil.projectmvi.mviapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jesil.projectmvi.mviapplication.R
import com.jesil.projectmvi.mviapplication.adapter.BlogAdapter
import com.jesil.projectmvi.mviapplication.utils.DataState
import com.jesil.projectmvi.mviapplication.utils.MainStateEvent
import com.jesil.projectmvi.mviapplication.utils.exhaustive
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    private val viewModel: MainViewModel by viewModels()

    private val blogAdapter : BlogAdapter by lazy {
        BlogAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    blogAdapter.setBlogData(dataState.data.toList())
                    blog_recycler_view.adapter = blogAdapter
                    displayProgressBar(false)
                    Log.d(TAG, "subscribeObservers: ${dataState.data}")
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                    Log.d(TAG, "subscribeObservers: ${dataState.exception.message}")
                }
                DataState.Loading -> {
                    displayProgressBar(true)
                }
            }.exhaustive

        })
    }

    private fun displayError(message: String?){
        if (message != null){
            text_view_error_message.text = message
        }
        else {
            text_view_error_message.text = "Unknown error!"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

}