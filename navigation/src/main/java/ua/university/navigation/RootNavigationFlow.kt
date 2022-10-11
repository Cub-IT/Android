package ua.university.navigation

import ua.university.navigation.flow.AuthFlow
import ua.university.navigation.flow.GroupFlow
import ua.university.navigation.navigator.Navigator

class RootNavigationFlow(private val navigator: Navigator) {
    fun start() {
        val authFlow = AuthFlow(navigator) { this.start() }
        val groupFlow = GroupFlow(navigator) { this.start() }
        val isUserAuthorized = true // change to true to see the flow of groups

        if (isUserAuthorized) {
            groupFlow.start()
        } else {
            authFlow.start()
        }
    }
}