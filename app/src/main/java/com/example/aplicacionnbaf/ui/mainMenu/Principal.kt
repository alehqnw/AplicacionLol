package com.example.aplicacionnbaf.ui.mainMenu


import android.media.MediaPlayer
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.aplicacionnbaf.R
import com.example.aplicacionnbaf.ui.addwindow.onCampeonAdded
import com.example.aplicacionnbaf.ui.delete.ChamDelGet

import com.example.aplicacionnbaf.ui.modelo.Rutas


var Champs:ArrayList<Campeones> = ArrayList<Campeones>()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla(navController: NavController){
    var deleteActive by remember{ mutableStateOf(false)}
    var estaActivoSB by remember{ mutableStateOf(false) }//Variable del SearchBar
    var TextoBuscar by remember { mutableStateOf("") }//Texto a buscar del SearchBar
    var filtroN by remember{ mutableStateOf(true)}
    var filtroL by remember{ mutableStateOf(false)}

    Champs = onCampeonAdded() as ArrayList<Campeones>

    PlayInitialSound()//Esta llamada al método pone una música al entrar a la aplicación


    Image(painter = painterResource(id = R.drawable.fondo), contentDescription = null, modifier = Modifier.fillMaxSize())//Imagen de fondo de la aplicación
    Column(modifier = Modifier
        .fillMaxSize()
        ) {
        //Esta imagen es el logo que aparece al principio del lol en pequeño
        Image(painter= painterResource(id = R.drawable.lollogo), contentDescription = null,
            Modifier
                .size(30.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally))


        /*
        *SearchBar(query = Champs) { Barra de busqueda para los campeones
        */
        SearchBar(query = TextoBuscar , onQueryChange = {TextoBuscar = it} , onSearch ={} , active = estaActivoSB, onActiveChange = {estaActivoSB = !estaActivoSB},
            modifier = Modifier
                .fillMaxWidth()
                .alpha(1f)
                //.border(width = 2.dp, color = lol, shape = )
                ) {
                Row {
                    Checkbox(checked = filtroN, onCheckedChange = { filtroN = it })
                    Text(text = "Filtrar por nombre",modifier = Modifier.padding(vertical = 15.dp))
                }
                Row {
                    Checkbox(checked = filtroL, onCheckedChange = { filtroL = it })
                    Text(text = "Filtrar por Linea",modifier = Modifier.padding(vertical = 15.dp))
                }

                var filtro:List<Campeones> = Champs

                if(filtroN){
                    filtro = Champs.filter { campeones ->
                        campeones.Nombre.toLowerCase().contains(TextoBuscar.toLowerCase())
                    }
                }
                if(filtroL){
                    filtro = Champs.filter { campeones ->
                        campeones.Lineas.contains(TextoBuscar.toUpperCase())
                    }
                }


                filtro.forEach{campeones ->
                        if(campeones.visible){
                            ChampCard(
                                Nombre = campeones.Nombre,
                                Linea = campeones.Lineas,
                                Imagen = campeones.Imagen,
                                Winrate = campeones.Winrate
                            )
                        }

                }
                /*
                Champs.forEach { campeones ->
                (if (campeones.Nombre.toLowerCase().contains(TextoBuscar.toLowerCase())) {
                    if(campeones.visible){
                        ChampCard(
                            Nombre = campeones.Nombre,
                            Linea = campeones.Lineas,
                            Imagen = campeones.Imagen,
                            Winrate = campeones.Winrate
                        )
                    }

                })

            }*/
            PlaySearchSound()
        }
        //Lista todo los campeones en formato Card
        LazyColumn(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()) {
            items(1){
                Champs.forEach{champ->(
                        if(champ.visible){
                            ChampCard(
                                Nombre = champ.Nombre,
                                Linea = champ.Lineas,
                                Imagen = champ.Imagen,
                                Winrate = champ.Winrate
                                )

                        }
                        )
                }
                
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()) {
            Button(onClick = { navController.navigate(Rutas.Anyadir.ruta) }) {
                Text(text = "Add")

            }
            Button(onClick = { navController.navigate(Rutas.Delete.ruta) }) {
                Text(text = "Delete")

                //Champs=ChamDelGet()

            }
        }
    }
}



@Composable
fun ChampCard(Nombre:String,Linea:List<String>,Winrate:Int,Imagen:Int){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        ,colors = CardDefaults.cardColors(Color.Gray)
    ) {
        Row {
            Image(
                painter = painterResource(id = Imagen),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .size(80.dp)
            )
            Column {
                Text(text = "Nombre del Campeón: "+Nombre,color = Color.White,modifier = Modifier.padding(horizontal=0.dp,vertical=0.dp))
                Text(text = "Linea "+Linea,color = Color.White)
                Text(text = "Winrate " + Winrate + "%", color = Color.White)
            }

        }
    }
}

@Composable
fun PlayInitialSound() {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? = null
    var Inicial:Int=0
    Inicial++
    if(Inicial ==1){
        DisposableEffect(context) {
            mediaPlayer = MediaPlayer.create(context, R.raw.entrada)
            mediaPlayer?.start()

            onDispose {
                mediaPlayer?.release()
            }
        }
    }

}
@Composable
fun PlaySearchSound() {
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? = null
    var Inicial:Int=0
    Inicial++
    if(Inicial ==1){
        DisposableEffect(context) {
            mediaPlayer = MediaPlayer.create(context, R.raw.buscar)
            mediaPlayer?.start()

            onDispose {
                mediaPlayer?.release()
            }
        }
    }

}


fun CargaChamp():ArrayList<Campeones>{
    val Champs:ArrayList<Campeones> = ArrayList<Campeones>()
    Champs.add(Campeones("Yasuo", arrayListOf("MID","ADC","TOP"), arrayListOf("LUCHADOR"),0, R.drawable.yasuo))
    Champs.add(Campeones("Mordekaiser", arrayListOf("TOP","JUNGLA"), arrayListOf("LUCHADOR","MAGO"),60,R.drawable.mordekaiser))
    Champs.add(Campeones("Briar", arrayListOf("JUNGLA"), arrayListOf("ASESINO"),57,R.drawable.briar))
    Champs.add(Campeones("Sett", arrayListOf("TOP"), arrayListOf("TANQUE"),50,R.drawable.sett))
    Champs.add(Campeones("Twitch", arrayListOf("TOP","JUNGLA","ADC","SUPPORT"), arrayListOf("TIRADOR"),83,R.drawable.twitch))
    Champs.add(Campeones("Yorick", arrayListOf("TOP","MID"), arrayListOf("LuCHADOR"),72,R.drawable.yorick))
    Champs.add(Campeones("Sona", arrayListOf("SUPPORT"), arrayListOf("MAGO"),48,R.drawable.sona))
    Champs.add(Campeones("Xerath", arrayListOf("MID","SUPPORT"), arrayListOf("MAGO"),52,R.drawable.xerath))
    Champs.add(Campeones("Shaco", arrayListOf("JUNGLA","SUPOORT"), arrayListOf("ASESINO"),46,R.drawable.shaco))
    Champs.add(Campeones("Miss Fortune", arrayListOf("ADC"), arrayListOf("TIRADOR"),38,R.drawable.missfortune))
    Champs.add(Campeones("Nilah", arrayListOf("ADC"), arrayListOf("LUCHADOR"),58,R.drawable.nilah))
    Champs.add(Campeones("Teemo", arrayListOf("TOP","MID","SUPPORT","ADC","JUNGLA"), arrayListOf("TIRADOR"),100,R.drawable.teemo))
    return Champs
}



