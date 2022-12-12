package com.rapptrlabs.androidtest.ui.animation

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.ui.common.CustomToolbar
import kotlin.math.roundToInt


@Composable
@Preview
fun AnimationScreenPreview() {
    AnimationScreen()
}

@Composable
fun AnimationScreen(
    navController: NavController? = null
) {
    Scaffold(
        topBar = {
            CustomToolbar(navController, R.string.animation_title, true)
        }
    ) { padding ->
        var offsetX by remember { mutableStateOf(100f) }
        var offsetY by remember { mutableStateOf(100f) }
        var isLogoVisible by remember { mutableStateOf(true) }

        val mMediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.sound)
        DisposableEffect(true) { // restart if dispatcher changes
            onDispose {
               mMediaPlayer.release()
            }
        }
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 56.dp)
                .padding(padding)
        ) {
            Image(
                painter = painterResource(R.drawable.gpc_logo),
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .alpha(
                        if (isLogoVisible) {
                            1f
                        } else {
                            0f
                        }
                    )
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        mMediaPlayer.start()
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                        }
                    }
            )

            FadeButton(
                modifier = Modifier
                    .height(55.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 26.dp),
                shouldBeEnabled = true,
                onClick = {
                    isLogoVisible = !isLogoVisible
                },
                text = if (isLogoVisible) {
                    stringResource(id = R.string.fade_out)
                } else {
                    stringResource(id = R.string.fade_in)
                }
            )
        }
    }
}

@Composable
fun FadeButton(modifier: Modifier, onClick: () -> Unit, shouldBeEnabled: Boolean, text: String) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.button_blue),
            contentColor = Color.White
        ),
        enabled = shouldBeEnabled,
        content = {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
        },
        modifier = modifier,
        onClick = onClick
    )
}
