package com.example.agrometeo.navigation

import kotlinx.serialization.descriptors.StructureKind

object Destinations {
    const val LOGIN = "login"
   // const val HOME = "home" ,it's cultures because it's the first shown
    const val CULTURES = "cultures"
    const val MAP ="map"
    const val FORCASTS = "forcasts"

    const val PROFILE = "profile"
}

enum class HomeContent(val route: String, val label: String) {
    CULTURES(Destinations.CULTURES, "Cultures"),
    MAP(Destinations.MAP, "Carte"),
    FORCASTS(Destinations.FORCASTS, "Prévisions"),
    PROFILE(Destinations.PROFILE,"Profil")
}