package com.example.unifyassignment.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unifyassignment.data.models.Mandate
import com.example.unifyassignment.data.repositories.MandateRepository

class MandateViewModel(private val repository: MandateRepository) : ViewModel() {

    val mandateLiveData: MutableLiveData<Mandate> = MutableLiveData()

    init {
        fetchData()
    }

    private fun fetchData() {
        val mandate = repository.fetchMandateData()
        mandateLiveData.value = mandate
    }
}
