package com.kappstudio.lazycolumnselect

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class UiState(
    val items: List<String> = (1..30).map { "Item $it" },
    val singleSelection: String = "",
    val multiSelections: List<String> = emptyList(),
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun singleSelect(item: String) {
        _uiState.update { currentState ->
            currentState.copy(
                singleSelection = item
            )
        }
    }

    fun multiSelect(item: String) {
        _uiState.update { currentState ->
            currentState.copy(
                multiSelections =
                if (item in currentState.multiSelections) {
                    currentState.multiSelections - item
                } else {
                    currentState.multiSelections + item
                }
            )
        }
    }
}