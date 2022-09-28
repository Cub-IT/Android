package ua.university.user.remote.entry

import com.google.gson.annotations.SerializedName

data class LogInRequestEntry(
    @SerializedName("login")
    val email: String,
    val password: String
)