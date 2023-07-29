package com.kappstudio.lazycolumnselect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kappstudio.lazycolumnselect.ui.theme.LazyColumnSelectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnSelectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: MainViewModel = viewModel()
                    val uiState = viewModel.uiState.collectAsState()

                    Row(modifier = Modifier.fillMaxSize()) {
                        SingleSelectionColumn(
                            items = uiState.value.items,
                            selectedItem = uiState.value.singleSelection,
                            onItemClicked = { viewModel.singleSelect(it) },
                            modifier = Modifier.weight(1f)
                        )
                        MultiSelectionColumn(
                            items = uiState.value.items,
                            selectedItems = uiState.value.multiSelections,
                            onItemClicked = { viewModel.multiSelect(it) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}