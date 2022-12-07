package ua.university.navigation

import ua.university.navigation.flow.AuthFlow
import ua.university.navigation.flow.GroupFlow
import ua.university.navigation.navigator.ComposeNavigation
import ua.university.preferences.UserSharedPreferences

class RootNavigationFlow constructor(
    private val navigator: ComposeNavigation,
    private val userSharedPreferences: UserSharedPreferences,
) {
    fun start() {
        val authFlow = AuthFlow(navigator) { this.start() }
        val groupFlow = GroupFlow(navigator) { this.start() }
        val isUserAuthorized = userSharedPreferences.getUser() != null

        if (isUserAuthorized) {
            groupFlow.start()
        } else {
            authFlow.start()
        }
    }
}