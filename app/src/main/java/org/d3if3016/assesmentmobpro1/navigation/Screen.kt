package org.d3if3016.assesmentmobpro1.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}