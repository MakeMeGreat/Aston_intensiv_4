package com.example.aston_intensiv_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                .replace(R.id.fragment_container_view, FragmentA())
                .addToBackStack(null)
                .commit()
        }

        binding.navigateToFragmentCButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentC.newInstance("Hello Fragment C"))
                .addToBackStack(null)
                .commit()
        }
    }

}