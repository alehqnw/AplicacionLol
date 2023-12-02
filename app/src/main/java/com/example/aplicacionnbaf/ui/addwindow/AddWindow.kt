package com.example.aplicacionnbaf.ui.addwindow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplicacionnbaf.R
import com.example.aplicacionnbaf.ui.delete.ChamDelGet
import com.example.aplicacionnbaf.ui.mainMenu.Campeones
import com.example.aplicacionnbaf.ui.mainMenu.CargaChamp
import com.example.aplicacionnbaf.ui.modelo.Rutas

var campeonDev = ChamDelGet() as ArrayList<Campeones>

@Composable
fun AgregarCampeonScreen(navController: NavController) {

    var nombre by remember { mutableStateOf("") }
    var lineas by remember { mutableStateOf("") }
    var tipo by remember{ mutableStateOf("")}
    var winrate by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Añadir nuevo campeón",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp)
            //elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre del campeón") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                TextField(
                    value = lineas,
                    onValueChange = { lineas = it },
                    label = { Text("Líneas (separadas por comas)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                TextField(
                    value = tipo,
                    onValueChange = { tipo = it },
                    label = { Text("Tipo (separadas por comas)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                TextField(
                    value = winrate,
                    onValueChange = { winrate = it },
                    label = { Text("Winrate (%)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { navController.popBackStack() },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Cancelar")
            }

            OutlinedButton(
                onClick = {
                    // Validar la entrada y guardar el nuevo campeón
                    if (nombre.isNotBlank() && lineas.isNotBlank() && winrate.isNotBlank()) {
                        var tipos = arrayListOf<String>()
                        tipos.addAll(tipo.toUpperCase().split(","))

                        var linea = arrayListOf<String>()
                        linea.addAll(lineas.toUpperCase().split(","))

                        System.out.println(tipos)
                        val nuevoCampeon = Campeones(nombre,
                            linea,tipos, winrate.toInt(), R.drawable.pordefecto)
                        campeonDev.add(nuevoCampeon)

                        navController.navigate(Rutas.Principal.ruta)
                    }
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Guardar")
            }
        }
    }
}
fun onCampeonAdded(): ArrayList<Campeones> {
    return campeonDev
}



