package com.example.aston_intensiv_4

import androidx.lifecycle.ViewModel
import com.example.aston_intensiv_4.data.User
import com.example.aston_intensiv_4.data.UserDataSource

class UserListViewModel : ViewModel() {

    val userList: MutableList<User> = UserDataSource.userList.toMutableList()

    fun updateUser(user: User) {
        val index = userList.indexOfFirst { it.id == user.id }
        userList[index] = user
    }
}