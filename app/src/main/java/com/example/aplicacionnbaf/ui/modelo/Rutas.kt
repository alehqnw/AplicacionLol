package com.example.aplicacionnbaf.ui.modelo

sealed class Rutas(val ruta:String) {
    object Anyadir:Rutas("addwindow")
    object Principal:Rutas("mainMenu")
    object Delete:Rutas("delete")
}