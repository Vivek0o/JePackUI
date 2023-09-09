package com.example.unifyassignment.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unifyassignment.data.repositories.MandateRepository
import com.example.unifyassignment.ui.composable.MandateScreen
import com.example.unifyassignment.ui.composable.TopBar
import com.example.unifyassignment.ui.theme.UnifyAssignmentTheme
import com.example.unifyassignment.ui.viewmodels.MandateViewModel
import com.example.unifyassignment.ui.viewmodels.MandateViewModelFactory

class MainActivity : ComponentActivity() {
    // Instantiate the Repository and ViewModelFactory
    private val mandateRepository by lazy { MandateRepository() }
    private val viewModelFactory by lazy { MandateViewModelFactory(mandateRepository) }

    // Use the factory to get the ViewModel
    private val viewModel by viewModels<MandateViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                TopBar()
                MandateScreen(viewModel)
            }
        }
    }
}
