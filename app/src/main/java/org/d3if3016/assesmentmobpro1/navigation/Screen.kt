package org.d3if3016.assesmentmobpro1.navigation

import org.d3if3016.assesmentmobpro1.ui.screen.KEY_ID_SERAGAM_OLAHRAGA

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_SERAGAM_OLAHRAGA}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}