package net.azarquiel.pockettasks.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import net.azarquiel.pockettasks.view.MainActivity

class MainViewModel(mainActivity: MainActivity): ViewModel() {
    val mainActivity by lazy { mainActivity }

    private val _variable =  MutableStateFlow(valorinicial)
    val variable: StateFlow<Boolean> = _variable
    //resto de variables y a continuacón las funciones
}