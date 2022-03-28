package com.example.caira15.activities.principal.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.caira15.databinding.FragmentHomeBinding

//import com.example.caira15.ui.principal.databinding.FragmentHomeBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }
//    supportFragmentManager.beginTransaction().replace(R.id.contenedorFragment,ListProgramFragment.newInstance()).commit()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}