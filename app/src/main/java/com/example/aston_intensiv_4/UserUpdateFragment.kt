package com.example.aston_intensiv_4

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.aston_intensiv_4.data.User
import com.example.aston_intensiv_4.databinding.FragmentUserUpdateBinding

class UserUpdateFragment : BaseFragment<FragmentUserUpdateBinding>(
    R.layout.fragment_user_update
) {
    private var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) userId = savedInstanceState.getInt(USER_ID)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserUpdateBinding {
        return FragmentUserUpdateBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(USER_TO_UPDATE_REQUEST) { _, bundle ->
            val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(USER_TO_UPDATE_KEY, User::class.java)
            } else {
                bundle.getSerializable(USER_TO_UPDATE_KEY) as User
            }
            binding.imageEditText.setText(user?.imageURI)
            binding.nameEditText.setText(user?.name)
            binding.lastNameEditText.setText(user?.lastName)
            binding.numberEditText.setText(user?.number)
            userId = user!!.id
        }

        binding.updateButton.setOnClickListener {
            val changedUser = User(
                userId,
                binding.imageEditText.text.toString(),
                binding.nameEditText.text.toString(),
                binding.lastNameEditText.text.toString(),
                binding.numberEditText.text.toString()
            )
            setFragmentResult(
                UPDATED_USER_REQUEST,
                bundleOf(UPDATED_USER_KEY to changedUser)
            )
            parentFragmentManager.popBackStack(APP_2_COMMIT, 0)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(USER_ID, userId)
    }

    companion object {
        const val APP_2_COMMIT = "APP_2_COMMIT"
        const val USER_TO_UPDATE_REQUEST = "USER_TO_UPDATE_REQUEST"
        const val UPDATED_USER_REQUEST = "UPDATED_USER_REQUEST"
        const val USER_TO_UPDATE_KEY = "USER_TO_UPDATE_KEY"
        const val UPDATED_USER_KEY = "UPDATED_USER_KEY"
        const val USER_ID = "USER_ID"
        fun newInstance(): UserUpdateFragment {
            return UserUpdateFragment()
        }
    }
}