package ua.university.navigation.flow

internal interface NavigationFlow {
    fun start()
    fun <T> getNavDirection(screenNavsClass: Class<T>): T?
}