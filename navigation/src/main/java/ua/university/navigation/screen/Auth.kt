package ua.university.navigation.screen

internal sealed class Auth(route: String) : NavTarget.Screen(route = "auth/$route") {
    internal object LogIn : Auth(route = "logIn")
    internal object SignUp : Auth(route = "signUp")
}