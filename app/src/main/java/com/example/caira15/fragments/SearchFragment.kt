package com.example.caira15.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.example.caira15.R
import com.example.caira15.activities.main.MainActivity


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)

        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner, Observer { status -> status?.let {
            viewModel.status.value=null
         //   Toast.makeText(this, "session close", Toast.LENGTH_SHORT).show()
          //  val intent = Intent(this, MainActivity::class.java)
        //    startActivity(intent)

        } })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}