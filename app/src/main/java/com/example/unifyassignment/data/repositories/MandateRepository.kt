package com.example.unifyassignment.data.repositories

import com.example.unifyassignment.data.models.Mandate
import com.example.unifyassignment.data.models.PaymentGateway
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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

    fun fetchPaymentGateway(): List<PaymentGateway> {
        val jsonData = """[
            {"name": "Gateway1", "details": "Card Detail1", "imageUrl": "https://apptestsoko.s3.ap-south-1.amazonaws.com/assets/a.png"
            },
            {"name": "Gateway2", "details": "Card Detail2", "imageUrl": "https://apptestsoko.s3.ap-south-1.amazonaws.com/assets/m.png"
            },
            {"name": "Gateway3", "details": "Card Detail3", "imageUrl": "https://apptestsoko.s3.ap-south-1.amazonaws.com/assets/fp.png"
            }
            ]
        """
        val gson = Gson()
        val type = object: TypeToken<List<PaymentGateway>>() {}.type
        return gson.fromJson(jsonData, type)
    }
}