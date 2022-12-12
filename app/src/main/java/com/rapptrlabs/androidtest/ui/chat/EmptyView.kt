package com.rapptrlabs.androidtest.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rapptrlabs.androidtest.R

@Composable
@Preview
fun EmptyView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            color = colorResource(id = R.color.chat_text),
            text = stringResource(id = R.string.emptyState),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}