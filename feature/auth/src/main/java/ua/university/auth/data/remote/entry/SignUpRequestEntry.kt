package ua.university.auth.data.remote.entry

import com.google.gson.annotations.SerializedName

data class SignUpRequestEntry(
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val email: String,
    val password: String
)