package net.azarquiel.pockettasks.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.azarquiel.pockettasks.R
import net.azarquiel.pockettasks.viewmodel.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = { CustomTopBar() },
        content = { padding ->
            CustomContent(padding, viewModel)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar() {
    TopAppBar(
        title = { Text(text = "PocketTasks") },
        colors = topAppBarColors(
            containerColor = colorResource(R.color.azulo),
            titleContentColor = colorResource(R.color.azulc)
        )
    )
}
@Composable
fun CustomContent(padding: PaddingValues, viewModel: MainViewModel) {
    val tareas by viewModel.tareas.collectAsState()
    var textoTarea by remember { mutableStateOf("") }
    //val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.azulo))
            .padding(padding)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top

    )
    {
        Row(
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = textoTarea,
                onValueChange = {textoTarea = it},
                label = {Text(text = "Añade una tarea")}
            )
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.azulc),
                    contentColor = colorResource(R.color.azulo)
                ),
                modifier = Modifier.padding(start = 10.dp),
                onClick = {
                if (textoTarea.isNotBlank()){
                    viewModel.añadirTarea(textoTarea)
                    textoTarea = ""
                }
            }) {
                Text(text = "Añadir")
            }
        }

        for (tarea in tareas) {
         Row(
             modifier = Modifier.padding(bottom = 2.dp)
         ) {
             Button(
                 colors = ButtonDefaults.buttonColors(
                     containerColor = colorResource(R.color.azulc),
                     contentColor = colorResource(R.color.azulo)
                 ),
                 modifier = Modifier.weight(1f),

                 onClick = { viewModel.completarTarea(tarea) }) {
                 Text(
                     text = if (tarea.completada) {
                         tarea.texto + " - (Completada)"
                     } else {
                         tarea.texto + " - (Pendiente)"
                     }
                 )
             }
             Button(
                 colors = ButtonDefaults.buttonColors(
                     containerColor = colorResource(R.color.rojo),
                     contentColor = colorResource(R.color.azulo)
                 ),
                 modifier = Modifier.padding(start = 10.dp),
                 onClick = { viewModel.eliminarTarea(tarea) }) {
                 Text(text = "Eliminar")
             }
         }
             }
         }
    }





