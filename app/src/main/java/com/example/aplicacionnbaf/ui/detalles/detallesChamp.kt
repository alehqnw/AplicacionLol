package com.example.aplicacionnbaf.ui.detalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.aplicacionnbaf.R
import com.example.aplicacionnbaf.ui.addwindow.onCampeonAdded
import com.example.aplicacionnbaf.ui.mainMenu.Campeones

@Composable
fun detalles(
    onDismissRequest: () -> Unit,
    NombreChamp:String
    ){
    val valdetalles = onCampeonAdded()
    Dialog(onDismissRequest = { onDismissRequest()}) {
        valdetalles.forEach{campeon->
            if(campeon.Nombre.contains(NombreChamp)){
                ChampDetalle(Nombre = campeon.Nombre,
                    Linea = campeon.Lineas,
                    Winrate = campeon.Winrate,
                    Imagen = campeon.Imagen,
                    Tipo = campeon.Tipo)
            }

            }
        }
    }
@Composable
fun ChampDetalle(Nombre:String,Linea:List<String>,Winrate:Int,Imagen:Int,Tipo:List<String>){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(400.dp)
        .padding(10.dp)
        ,colors = CardDefaults.cardColors(Color.Blue)
    ) {
            Image(
                painter = painterResource(id = Imagen),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .fillMaxWidth()
            )
            Column {
                Text(text = "Nombre del Campeón: "+Nombre,color = Color.White,modifier = Modifier.padding(horizontal=20.dp,vertical=10.dp))
                Text(text = "Linea "+Linea,color = Color.White,modifier = Modifier.padding(horizontal=20.dp,vertical=10.dp))
                Text(text = "Tipo "+Tipo,color=Color.White,modifier = Modifier.padding(horizontal=20.dp,vertical=10.dp))
                Text(text = "Winrate " + Winrate + "%", color = Color.White,modifier = Modifier.padding(horizontal=20.dp,vertical=10.dp))

            }


    }
}