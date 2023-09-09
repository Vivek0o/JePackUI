package com.example.unifyassignment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unifyassignment.data.models.Mandate
import com.example.unifyassignment.data.models.PaymentGateway
import com.example.unifyassignment.data.repositories.MandateRepository

class MandateViewModel(private val repository: MandateRepository) : ViewModel() {

    val mandateLiveData: MutableLiveData<Mandate> = MutableLiveData()
    private val _paymentGateways = MutableLiveData<List<PaymentGateway>>(emptyList())
    val paymentLiveDate: LiveData<List<PaymentGateway>> = _paymentGateways

    init {
        fetchData()
    }

    private fun fetchData() {
        val mandate = repository.fetchMandateData()
        mandateLiveData.value = mandate

        val payment = repository.fetchPaymentGateway()
        _paymentGateways.value = payment
    }
}
