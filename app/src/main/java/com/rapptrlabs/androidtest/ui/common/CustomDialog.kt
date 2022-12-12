package com.rapptrlabs.androidtest.ui.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomDialog(
    title: String,
    message: String,
    buttonText: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = title) },
        text = { Text(message) },
        confirmButton = { Button(onClick = { onDismiss() }) { Text(buttonText) } },
    )
}