package com.example.aston_intensiv_4.app1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_intensiv_4.BaseFragment
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentBBinding

class FragmentB : BaseFragment<FragmentBBinding>(
    R.layout.fragment_b
) {

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentBBinding {
        return FragmentBBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.backToFragmentAButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentA.newInstance())
                .addToBackStack(null)
                .commit()
        }
        val textToB = "Hello Fragment C"

        binding.navigateToFragmentCButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentC.newInstance(textToB))
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance(): FragmentB {
            return FragmentB()
        }
    }

}