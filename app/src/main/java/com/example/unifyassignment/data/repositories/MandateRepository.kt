package com.example.unifyassignment.data.repositories

import com.example.unifyassignment.data.models.Mandate
import com.google.gson.Gson

class MandateRepository {

    // Simulating a fetch from a data source (could be network, database, etc.)
    fun fetchMandateData(): Mandate {
        val jsonData = """
            {
                "validTill": "29-May-2024",
                "amount": "UGX-10,000",
                "frequency": "As presented",
                "message": "The amount will be blocked when ride is booked with Safebods, subject to above mentioned limit and validity. The limit is "
                
            }
        """
        return Gson().fromJson(jsonData, Mandate::class.java)
    }
}