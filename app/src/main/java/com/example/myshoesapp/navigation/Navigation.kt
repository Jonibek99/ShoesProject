package com.example.myshoesapp.navigation


import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myshoesapp.navigation.Screens
import com.example.myshoesapp.addNew.AddNewActivity
import com.example.myshoesapp.detailedView.DetailedView
import com.example.myshoesapp.list.ShoesList

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screens.ShoesListScreen.route) {
        composable(Screens.ShoesListScreen.route) {
            ShoesList(onAddNewShoesClick = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
            }, onShoesClick = { shoesId ->
                navController.navigate("detailedView/$shoesId")
            }
            )
        }



        composable(
            route = "detailedView/{ShoesId}"
        ) { backStackEntry ->
            DetailedView(shoesId = backStackEntry.arguments?.getString("ShoesId")!!)
        }
    }
}