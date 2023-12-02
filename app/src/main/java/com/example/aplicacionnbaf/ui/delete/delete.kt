package com.example.aplicacionnbaf.ui.delete

import android.graphics.Paint.Align
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplicacionnbaf.ui.mainMenu.Campeones
import com.example.aplicacionnbaf.ui.mainMenu.CargaChamp
import com.example.aplicacionnbaf.ui.mainMenu.Champs
import com.example.aplicacionnbaf.ui.mainMenu.Pantalla
import com.example.aplicacionnbaf.ui.mainMenu.PlaySearchSound
import com.example.aplicacionnbaf.ui.modelo.Rutas


@Composable
private fun ChampCard(Nombre:String,Linea:ArrayList<String>,Winrate:Int,Imagen:Int){
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
                Row(modifier = Modifier.fillMaxWidth()) {

                }
            }

        }
    }
}

fun deleteArray(Campeon:ArrayList<Campeones>, Nombre: String): ArrayList<Campeones> {

    Campeon.forEach { campeones ->
        (if (campeones.Nombre == Nombre) {
            //Campeon.removeAt(Campeon.indexOf(campeones))
            campeones.visible=false
        })
    }
    System.out.println(Campeon.size)
    return Campeon
}
var Champeon = CargaChamp()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DelWin(navController: NavController){
    var estaActivoSB by remember { mutableStateOf(false) }//Variable del SearchBar
    var TextoBuscar by remember { mutableStateOf("") }//Texto a buscar del SearchBar
    var ChamnewList: ArrayList<Campeones> = ArrayList<Campeones>()//Ponerarrray



    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(
            query = TextoBuscar,
            onQueryChange = { TextoBuscar = it },
            onSearch = {},
            active = estaActivoSB,
            onActiveChange = { estaActivoSB = !estaActivoSB },
            modifier = Modifier
                .fillMaxWidth()
                .alpha(1f)
            //.border(width = 2.dp, color = lol, shape = )
        ) {
            Champeon.forEach { campeones ->
                (if (campeones.Nombre.toLowerCase().contains(TextoBuscar.toLowerCase())) {
                    if(campeones.visible){
                        ChampCard(
                            Nombre = campeones.Nombre,
                            Linea = campeones.Lineas,
                            Imagen = campeones.Imagen,
                            Winrate = campeones.Winrate
                        )
                        Button(onClick = {Champeon = deleteArray(
                            Campeon = Champeon as ArrayList<Campeones>,
                            Nombre = campeones.Nombre
                        );
                            if (navController != null) {
                                navController.navigate(Rutas.Principal.ruta)
                            }
                        }) {
                            Text(text = "Eliminar");

                        }
                    }

                })


            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()) {
            item{
                Champs.forEach{ champ->(
                        if(champ.visible){
                            ChampCard(
                                Nombre = champ.Nombre,
                                Linea = champ.Lineas,
                                Imagen = champ.Imagen,
                                Winrate = champ.Winrate
                            )
                            Button(onClick = {Champeon = deleteArray(Champeon as ArrayList<Campeones>, champ.Nombre);
                                /*if (navController != null) {
                                navController.navigate(Rutas.Principal.ruta)
                                }*/
                            }) {
                                Text(text = "Eliminar");

                            }
                        }
                       )

                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    Button(onClick = { navController.navigate(Rutas.Principal.ruta) }) {
                        Text(text = "Guardar")
                    }
                }

            }
        }
    }

}
fun ChamDelGet(): List<Campeones> {
    return Champeon
}

