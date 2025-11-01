package com.example.task_one.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.task_one.viewmodel.CiJobViewModel

@Composable
fun CiJobList(viewModel: CiJobViewModel, padding: PaddingValues = PaddingValues()) {
    LazyColumn(modifier = Modifier.padding(padding)) {
        items(viewModel.jobs) { job ->
            Text(
                text = job.name,
                color = when(job.color.lowercase()) {
                    "red" -> Color.Red
                    "blue" -> Color.Green
                    else -> Color.Gray
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}