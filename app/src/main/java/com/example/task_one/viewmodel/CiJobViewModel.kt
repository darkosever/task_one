package com.example.task_one.viewmodel

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateListOf
import com.example.task_one.model.CiJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CiJobViewModel(private val playSound: (MediaPlayer) -> Unit = { it.start() }) : ViewModel() {

    val jobs = mutableStateListOf<CiJob>()
    private val previousJobs = mutableListOf<CiJob>()

    private val warningSoundUrl = "https://www.dropbox.com/s/ukzsaet10hbekbq/doh1_y.wav?dl=0"

    init {
        startPolling()
    }

    private fun startPolling() {
        viewModelScope.launch {
            while (true) {
                fetchJobs()
                delay(30_000)
            }
        }
    }

    private suspend fun fetchJobs() {
        try {
            val newJobs = CiService.api.getJobs().jobs

            newJobs.forEach { newJob ->
                val oldJob = previousJobs.find { it.name == newJob.name }
                if (oldJob != null &&
                    oldJob.color.lowercase() != newJob.color.lowercase() &&
                    newJob.color.lowercase() == "red"
                ) {
                    val mediaPlayer = MediaPlayer().apply {
                        setDataSource(warningSoundUrl)
                        prepare()
                    }
                    playSound(mediaPlayer)
                }
            }

            previousJobs.clear()
            previousJobs.addAll(newJobs)

            jobs.clear()
            jobs.addAll(newJobs)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}