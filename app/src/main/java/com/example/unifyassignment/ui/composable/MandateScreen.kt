package com.example.unifyassignment.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unifyassignment.data.models.Mandate
import com.example.unifyassignment.ui.viewmodels.MandateViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandateScreen(viewModel: MandateViewModel) {

    val mandate by viewModel.mandateLiveData.observeAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
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
            }
        Spacer(modifier = Modifier.height(16.dp))
        ElevatedCard {
            Modifier.fillMaxWidth().height(100.dp)
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
