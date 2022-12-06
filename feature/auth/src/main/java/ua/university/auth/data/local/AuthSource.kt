package ua.university.auth.data.local

interface AuthSource {
    fun saveUser(userItem: UserItem)
}