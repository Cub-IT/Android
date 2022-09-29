package ua.university.user.local

import ua.university.user.model.User

interface UserSource {

    fun isAuthorized(): Boolean = getUser() != null

    fun getUser(): User?

    fun saveUser(userItem: User)

    fun deleteUser()

}