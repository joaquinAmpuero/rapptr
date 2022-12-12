package com.rapptrlabs.androidtest.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.ui.common.CustomToolbar
import com.rapptrlabs.androidtest.ui.navigation.NavRoute

@Preview(showBackground = true)
@Composable
fun HomeViewPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen(navController: NavController? = null) {
    Scaffold(
        topBar = { CustomToolbar(navController, R.string.activity_main_title, false) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            Image(
                painter = painterResource(R.drawable.gpc_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                ChatRow(
                    icon = R.drawable.ic_chat,
                    text = stringResource(id = R.string.chat_button),
                    onClick = { navController?.navigate(NavRoute.Chat.path) }
                )
                ChatRow(
                    icon = R.drawable.ic_lock,
                    text = stringResource(id = R.string.login_button),
                    onClick = { navController?.navigate(NavRoute.Login.path) }
                )
                ChatRow(
                    icon = R.drawable.ic_animation,
                    text = stringResource(id = R.string.animation_button),
                    onClick = {navController?.navigate(NavRoute.Animation.path)}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatRowPreview() {
    ChatRow(icon = R.drawable.ic_chat, text = stringResource(id = R.string.chat_button), { })
}

@Composable
fun ChatRow(@DrawableRes icon: Int, text: String, onClick: () -> Unit) {
    MaterialTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .clickable {
                    onClick()
                },
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = colorResource(
                id = R.color.background
            ).copy(alpha = 0.8f)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 22.dp, end = 22.dp, top = 16.dp, bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = text,
                    color = colorResource(id = R.color.chat_text),
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
