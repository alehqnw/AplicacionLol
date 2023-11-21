package com.example.aplicacionnbaf.ui.mainMenu

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplicacionnbaf.R
import java.security.Principal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pantalla(){
    var Champs:ArrayList<Campeones> = ArrayList<Campeones>()
    Champs.add(Campeones("Yasuo", arrayListOf("MID","ADC","TOP"), arrayListOf("LUCHADOR"),"5<0%", R.drawable.yasuo))
    Champs.add(Campeones("Mordekaiser", arrayListOf("TOP","JUNGLA"), arrayListOf("LUCHADOR","MAGO"),"60%",R.drawable.mordekaiser))



    var TextoLazy by remember { mutableStateOf(" ") }
    var estaActivoSB by remember{ mutableStateOf(false) }
    var TextoBuscar by remember { mutableStateOf("") }
    System.out.println("Size del ChampaRRAYLIST "+Champs.size)
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        Text(text = "Bienvenido a la grieta del invocador",
            modifier= Modifier
                .weight(0.7f)
                .fillMaxWidth()) //Insertar música y foto aquí o previo
        Text(text = "InsertarBuscador", modifier = Modifier
            .weight(0.5f)
            .fillMaxWidth())
        /*SearchBar(query = Champs) { Barra de busqueda para los campeones
            
        }*/
        SearchBar(query = TextoBuscar , onQueryChange = {TextoBuscar = it} , onSearch ={} , active = estaActivoSB, onActiveChange = {estaActivoSB = !estaActivoSB} ) {
            Champs.forEach{ campeones ->  Text(campeones.Nombre)}
        }
        LazyColumn(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()) {
            items(10){
                champ->(run {
                for (champ in Champs) {
                    ChampCard(
                        Nombre = champ.Nombre,
                        Linea = champ.Lineas,
                        Imagen = champ.Imagen,
                        Winrate = champ.Winrate
                    )
                }
            })
                //ChampCard(Nombre = Champs.get(0).Nombre, Linea = Champs.get(0).Lineas, Imagen = Champs.get(0).Imagen, Winrate = Champs.get(0).Winrate)
            }
        }
        Row(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Add")

            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Delete")

            }
        }
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PrevPantalla(){
    Pantalla()
}

@Composable
fun ChampCard(Nombre:String,Linea:ArrayList<String>,Winrate:String,Imagen:Int){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row {
            Image(
                painter = painterResource(id = Imagen),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .size(80.dp)
            )
            Column {
                Text(text = "Nombre del Campeón "+Nombre,color = Color.White,modifier = Modifier.padding(horizontal=0.dp,vertical=0.dp))
                Text(text = "Linea "+Linea,color = Color.White)
            }

        }
    }
}