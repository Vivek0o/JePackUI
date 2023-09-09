package com.example.unifyassignment.ui.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.unifyassignment.data.models.Mandate
import com.example.unifyassignment.data.models.PaymentGateway
import com.example.unifyassignment.ui.viewmodels.MandateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandateScreen(viewModel: MandateViewModel) {

    val mandate by viewModel.mandateLiveData.observeAsState()
    val paymentGateway = viewModel.paymentLiveDate.observeAsState(initial = emptyList()).value
    var selectedPaymentGateway by remember {
        mutableStateOf<PaymentGateway?>(null) }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        ElevatedCard(onClick = {/* TODO*/ }) {
            FirstCardContent(mandate)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Autopay Payment Options", style = TextStyle(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
            paymentGateway.forEach { gateway ->
                paymentCardContent(paymentGateway = gateway) {
                    Log.e("MandateScreen", "Cardclicked:  ${it.name}")
                    selectedPaymentGateway = it
                }
            }
            }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCard {
            Modifier
                .fillMaxWidth()
            selectedPaymentGateway?.let {
                payment -> Text(text = "${payment.name} - ${payment.details}")
                Log.e("MandateScreen", "Cardclicked:  ${payment.name}")
            }
        }
    }
}

@Composable
fun FirstCardContent(mandate: Mandate?) {
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row {
            Text(text = buildAnnotatedString {
                append(text = "Valid till - ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)) {
                    append(mandate?.validTill)
                }
            }, fontSize = 12.sp, fontWeight = FontWeight.Light)
            Spacer(Modifier.weight(1f))
            Text(text = buildAnnotatedString {
                append("Upto Amount - ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)) {
                    append(mandate?.amount)
                }
            }, fontSize = 12.sp, fontWeight = FontWeight.Light)
        }
        Divider(color = Color.Black, thickness = 1.5.dp)
        Text(text = buildAnnotatedString {
            append("Frequency - ")
            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)) {
                append(mandate?.frequency)
            }
        }, fontSize = 12.sp, fontWeight = FontWeight.Light)
        Divider(color = Color.Black, thickness = 1.5.dp)
        ElevatedCard {
            Text(color = Color(0xfff2b36f),
                modifier = Modifier.padding(10.dp),
                text = buildAnnotatedString {
                    append(mandate?.message)
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    ) {
                        append(mandate?.amount)
                    }
                }
            )
        }
    }
}

@Composable
fun paymentCardContent(
    paymentGateway: PaymentGateway,
    onClick: (PaymentGateway) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick(paymentGateway) }
            .padding(end = 8.dp)
            .fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = rememberImagePainter(data = paymentGateway.imageUrl),
                contentDescription = paymentGateway.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

}
