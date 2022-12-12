package com.rapptrlabs.androidtest.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rapptrlabs.androidtest.R

@Composable
fun CustomToolbar(
    navController: NavController? = null,
    @StringRes textId: Int,
    showBackButton: Boolean = false
) {
    if (showBackButton) {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.colorPrimary),
            title = {
                Text(
                    color = Color.White,
                    text = stringResource(
                        id = textId,
                    )
                )
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    tint = Color.White,
                    contentDescription = "",
                    modifier = Modifier.clickable { navController?.navigateUp() }
                )
            }
        )
    } else {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.colorPrimary),
            title = {
                Text(
                    color = Color.White,
                    text = stringResource(
                        id = textId,
                    )
                )
            }
        )
    }
}
