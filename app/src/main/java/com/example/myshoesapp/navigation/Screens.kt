package com.example.myshoesapp.navigation

sealed class Screens(val route: String) {
    object ShoesListScreen: Screens("shoes_list")
}
