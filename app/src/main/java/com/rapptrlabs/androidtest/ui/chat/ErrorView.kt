package com.rapptrlabs.androidtest.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rapptrlabs.androidtest.R

@Composable
@Preview
fun errorViewPreview() {
    ErrorView("No content available", "Retry", {})
}

@Composable
fun ErrorView(message: String, buttonText: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp,Alignment.CenterVertically),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = colorResource(id = R.color.button_blue),
            text = message,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.button_blue),
                contentColor = Color.White
            ),
            content = {
                Text(
                    text = buttonText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                )
            },
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 25.dp, end = 26.dp),
            onClick = onClick
        )
    }
}