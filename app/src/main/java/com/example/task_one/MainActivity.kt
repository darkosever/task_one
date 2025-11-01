package com.example.task_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.example.task_one.ui.CiJobList
import com.example.task_one.ui.theme.Task_oneTheme
import com.example.task_one.viewmodel.CiJobViewModel

class MainActivity : ComponentActivity() {
    private val viewModel = CiJobViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Task_oneTheme {
                Scaffold { innerPadding ->
                    CiJobList(viewModel = viewModel, padding = innerPadding)
                }
            }
        }
    }
}