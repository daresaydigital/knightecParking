package se.daresay.app

import androidx.navigation.NavController

sealed class Node(val route: String) {
    object SignIn: Node("signin")
    object CarConnection: Node("carconnection")
}

fun NavController.navigate(nav: Node){
    navigate(nav.route)
}