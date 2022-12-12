package com.rapptrlabs.androidtest.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.domain.entity.ChatMessageEntity
import com.rapptrlabs.androidtest.ui.chat.viewModel.state.ChatState
import com.rapptrlabs.androidtest.ui.chat.viewModel.ChatViewModel
import com.rapptrlabs.androidtest.ui.common.CustomToolbar

@Composable
@Preview
fun chatPreview() {
    ChatScreen(viewModel = hiltViewModel())
}

@Composable
fun ChatScreen(
    navController: NavController? = null,
    viewModel: ChatViewModel
) {

    Scaffold(
        topBar = {
            CustomToolbar(navController, R.string.chat_title, true)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colors.background)
        ) {
            val state = viewModel.chatState.collectAsState()
            when (state.value) {
                is ChatState.EmptyResults -> {
                    ErrorView(
                        stringResource(id = R.string.emptyState),
                        stringResource(id = R.string.retry)
                    ) { viewModel.getChat() }
                }
                is ChatState.Error -> {
                    ErrorView(
                        stringResource(id = R.string.errorState),
                        stringResource(id = R.string.retry),
                    ) { viewModel.getChat() }
                }
                is ChatState.InitialState -> {}
                is ChatState.Loading -> { LoadingView() }
                is ChatState.Success -> {
                    ChatList((state.value as ChatState.Success).chatMessages)
                }
            }
        }
    }
}

@Composable
fun ChatList(chatList: List<ChatMessageEntity>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        chatList.forEach { element ->
            item {
                chatBubble(message = element)
            }
        }
    }
}

@Composable
@Preview
fun chatBubblePreview() {
    chatBubble(
        ChatMessageEntity(
            userId = 123456,
            avatarUrl = "https://img.freepik.com/vector-premium/perfil-hombre-dibujos-animados_18591-58482.jpg?w=2000",
            username = "Test",
            message = "This is a test message for the preview. This is a test message for the preview.This is a test message for the preview. This is a test message for the preview."
        )
    )
}

@Composable
fun chatBubble(message: ChatMessageEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
    ) {
        Row {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(message.avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_avatar_placeholder),
                contentDescription = "${message.username}'s avatar image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(64.dp)
                    .height(64.dp)
            )

            Box(modifier = Modifier.padding(start = 7.dp)) {
                Column {
                    Text(
                        color = colorResource(id = R.color.chat_text),
                        text = message.username,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            color = colorResource(id = R.color.chat_text),
                            text = message.message,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}