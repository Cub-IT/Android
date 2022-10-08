package ua.university.navigation.navigator

import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.NavTarget

interface Navigator {
    fun navigateTo(navTarget: NavTarget, navigationFlow: NavigationFlow)
}