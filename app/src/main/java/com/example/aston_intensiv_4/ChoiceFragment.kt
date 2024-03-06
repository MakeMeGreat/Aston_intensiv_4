package com.example.aston_intensiv_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_intensiv_4.databinding.FragmentChoiceBinding

private const val FRAGMENT_A_COMMIT = "FRAGMENT_A_COMMIT"

class ChoiceFragment : BaseFragment<FragmentChoiceBinding>(
    R.layout.fragment_choice
) {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChoiceBinding {
        return FragmentChoiceBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.app1Button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .setPrimaryNavigationFragment(this)
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container_view, FragmentA())
                .addToBackStack(FRAGMENT_A_COMMIT)
                .commit()
        }

        binding.app2Button.setOnClickListener {
            //Todo: add second part of homework
        }
    }
}