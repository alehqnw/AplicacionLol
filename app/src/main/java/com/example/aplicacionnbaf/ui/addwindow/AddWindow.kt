package com.example.aplicacionnbaf.ui.addwindow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.aplicacionnbaf.ui.modelo.Rutas

@Composable
fun AddWin(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)) {



        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(2f)) {

            Button(onClick = { navController.navigate(Rutas.Principal.ruta)}) {
                Text(text = "Añadir")
            }
        }
    }
}