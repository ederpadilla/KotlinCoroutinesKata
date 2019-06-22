package com.example.coroutineskata.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.coroutineskata.R
import com.example.coroutineskata.api.ConnectivityIntercpetorImpl
import com.example.coroutineskata.api.MoviesClient
import com.example.coroutineskata.api.MoviesDataSource
import com.example.coroutineskata.api.MoviesDataSourceImpl
import kotlinx.android.synthetic.main.current_wather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWatherFragment()
    }

    private lateinit var viewModel: CurrentWatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_wather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = MoviesClient(ConnectivityIntercpetorImpl(this.context!!))
        val moviesDataSource = MoviesDataSourceImpl(apiService)
        moviesDataSource.downloadMovieInfo.observe(this, Observer {
            textView.text = it.toString()
        })
        GlobalScope.launch(Dispatchers.Main) {
            progressBar.visibility = View.VISIBLE
            moviesDataSource.fetchCurrentMovie("320288","es-MX")
            progressBar.visibility = View.GONE
        }
    }

}
