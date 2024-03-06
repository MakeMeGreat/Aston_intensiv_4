package com.example.aston_intensiv_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aston_intensiv_4.databinding.FragmentCBinding

private const val PHRASE_KEY = "PHRASE_KEY"
private const val FRAGMENT_A_COMMIT = "FRAGMENT_A_COMMIT"

class FragmentC : BaseFragment<FragmentCBinding>(
    R.layout.fragment_c
) {

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCBinding {
        return FragmentCBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputPhrase = requireArguments().getString(PHRASE_KEY)
        binding.textHolder.text = inputPhrase

        binding.backToFragmentAButton.setOnClickListener {
            parentFragmentManager.popBackStack(FRAGMENT_A_COMMIT, 0)
        }

        binding.navigateToFragmentDButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, FragmentD())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {
        fun newInstance(inputPhrase: String): FragmentC {
            return FragmentC().apply {
                arguments = Bundle().also { it.putString(PHRASE_KEY, inputPhrase) }
            }
        }
    }
}