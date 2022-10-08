package ua.university.navigation.navigator

import ua.university.navigation.flow.NavigationFlow
import ua.university.navigation.screen.NavTarget

abstract class Navigator {
    internal abstract fun navigateTo(navTarget: NavTarget, navigationFlow: NavigationFlow)
}