package com.rapptrlabs.androidtest.ui.splash

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
import androidx.compose.ui.layout.ContentScale
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
import com.rapptrlabs.androidtest.ui.navigation.NavRoute
import kotlin.math.roundToInt
import kotlinx.coroutines.delay


@Composable
@Preview
fun SplashScreenPreview() {
    SplashScreen()
}

@Composable
fun SplashScreen(
    navController: NavController? = null
) {
    Scaffold() { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                painter = painterResource(R.drawable.gpc_splash),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            LaunchedEffect(navController) {
                delay(2000)
                navController?.navigate(NavRoute.Home.path)
            }

        }
    }
}
