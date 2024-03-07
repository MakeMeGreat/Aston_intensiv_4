package com.example.aston_intensiv_4.app1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_intensiv_4.BaseFragment
import com.example.aston_intensiv_4.R
import com.example.aston_intensiv_4.databinding.FragmentABinding

private const val FRAGMENT_B_COMMIT = "FRAGMENT_B_COMMIT"

class FragmentA : BaseFragment<FragmentABinding>(
    R.layout.fragment_a
) {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentABinding {
        return FragmentABinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigateToFragmentBButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentB.newInstance())
                .addToBackStack(FRAGMENT_B_COMMIT)
                .commit()
        }
    }

    companion object {
        fun newInstance(): FragmentA {
            return FragmentA()
        }
    }
}