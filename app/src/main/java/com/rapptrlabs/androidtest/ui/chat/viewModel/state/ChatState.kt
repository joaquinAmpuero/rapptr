package com.rapptrlabs.androidtest.ui.chat.viewModel.state

import androidx.annotation.Keep
import com.rapptrlabs.androidtest.domain.entity.ChatMessageEntity

@Keep
sealed class ChatState {
    object InitialState : ChatState()
    object Loading : ChatState()
    object EmptyResults : ChatState()
    data class Error(val errorMessage: String) : ChatState()
    data class Success(val chatMessages: List<ChatMessageEntity>) : ChatState()
}
