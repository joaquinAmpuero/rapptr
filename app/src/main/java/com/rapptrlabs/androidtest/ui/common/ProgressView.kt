package com.rapptrlabs.androidtest.ui.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
private fun CustomCircularProgressBar(){
    CircularProgressIndicator(
        modifier = Modifier.size(100.dp),
        color = Color.Green,
        strokeWidth = 10.dp)

}
@Composable
@Preview
private fun CustomCircularProgressBarPreview(){
    CustomCircularProgressBar()
}