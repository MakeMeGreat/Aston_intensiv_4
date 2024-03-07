package com.example.aston_intensiv_4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import coil.size.Scale
import com.example.aston_intensiv_4.data.User
import com.example.aston_intensiv_4.databinding.FragmentUserItemBinding

class UserListAdapter(
    private val onUserClicked: (User) -> Unit,
) : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffUtil) {

    class UserViewHolder(private val binding: FragmentUserItemBinding) : ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.imageHolder.load(user.imageURI) {
                scale(Scale.FILL)
                placeholder(R.drawable.image_holder)
                error(R.drawable.error_img)

            }
            binding.name.text = user.name
            binding.lastName.text = user.lastName
            binding.number.text = user.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(
            FragmentUserItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onUserClicked(current)
        }
        holder.bind(current)
    }

    companion object {
        private val UserDiffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return newItem == oldItem
            }
        }
    }
}