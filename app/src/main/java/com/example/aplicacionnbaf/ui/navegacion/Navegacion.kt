package com.example.aplicacionnbaf.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionnbaf.ui.addwindow.AddWin
import com.example.aplicacionnbaf.ui.modelo.Rutas
import com.example.aplicacionnbaf.ui.mainMenu.Pantalla


@Composable
fun GrafoNavegacion(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.Principal.ruta){

        composable(Rutas.Principal.ruta){
            Pantalla(navController = navController)
        }
        composable(Rutas.Anyadir.ruta){
            AddWin(navController = navController)
        }
    }

}