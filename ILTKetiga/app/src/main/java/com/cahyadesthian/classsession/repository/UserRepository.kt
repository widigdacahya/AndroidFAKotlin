package com.cahyadesthian.classsession.repository

import com.cahyadesthian.classsession.UserResponse

interface UserRepository {

    fun getUsers(page: Int, callback: (UserResponse))

}