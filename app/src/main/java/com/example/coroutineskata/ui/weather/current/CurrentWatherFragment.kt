package com.example.coroutineskata.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.coroutineskata.R

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
    }

}
