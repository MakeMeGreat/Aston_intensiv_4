package com.example.aston_intensiv_4

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.aston_intensiv_4.data.User
import com.example.aston_intensiv_4.databinding.FragmentUserListBinding

class UserListFragment : BaseFragment<FragmentUserListBinding>(
    R.layout.fragment_user_list
) {

    private val viewModel: UserListViewModel by activityViewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUserListBinding {
        return FragmentUserListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = UserListAdapter {
            val user = it
            setFragmentResult(USER_TO_UPDATE_REQUEST, bundleOf(USER_TO_UPDATE_KEY to user))
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, UserUpdateFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        adapter.submitList(viewModel.userList)
        binding.recyclerView.adapter = adapter

        setFragmentResultListener(UPDATED_USER_REQUEST) { _, bundle ->
            val updatedUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(UPDATED_USER_KEY, User::class.java)
            } else {
                bundle.getSerializable(UPDATED_USER_KEY) as User
            }
            viewModel.updateUser(updatedUser!!)
        }
    }

    companion object {
        const val USER_TO_UPDATE_REQUEST = "USER_TO_UPDATE_REQUEST"
        const val UPDATED_USER_REQUEST = "UPDATED_USER_REQUEST"
        const val USER_TO_UPDATE_KEY = "USER_TO_UPDATE_KEY"
        const val UPDATED_USER_KEY = "UPDATED_USER_KEY"
        fun newInstance(): UserListFragment {
            return UserListFragment()
        }
    }
}