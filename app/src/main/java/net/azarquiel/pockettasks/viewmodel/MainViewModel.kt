package net.azarquiel.pockettasks.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.azarquiel.pockettasks.model.Tarea
import net.azarquiel.pockettasks.view.MainActivity

class MainViewModel(mainActivity: MainActivity): ViewModel() {
    val mainActivity by lazy { mainActivity }

    private val _tareas = MutableStateFlow(
        listOf<Tarea>()
    )


    val tareas: StateFlow<List<Tarea>> = _tareas

    //resto de variables y a continuacón las funciones


    fun completarTarea(tarea: Tarea) {
        val nuevaLista = _tareas.value.map { elemento ->
            if (elemento == tarea) {
                tarea.copy(completada = !tarea.completada)
            } else {
                elemento
            }
        }
        _tareas.value = nuevaLista
    }

    fun añadirTarea(texto: String) {
        var nuevaTarea = Tarea(texto = texto, completada = false)
        _tareas.value += nuevaTarea
    }

    fun eliminarTarea(tarea: Tarea) {
        val nuevaLista = _tareas.value.filter { elemento ->
            elemento != tarea
        }
        _tareas.value = nuevaLista
    }

}