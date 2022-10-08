package ua.university.navigation.screen

internal sealed class Auth(route: String) : NavTarget.Screen(route = "auth/$route") {
    internal object SignIn : Auth(route = "signIn")
    internal object SignUp : Auth(route = "signUp")
}