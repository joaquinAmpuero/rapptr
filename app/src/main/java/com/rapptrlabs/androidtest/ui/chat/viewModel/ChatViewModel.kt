package com.rapptrlabs.androidtest.ui.chat.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rapptrlabs.androidtest.domain.repository.ChatMessageRepository
import com.rapptrlabs.androidtest.ui.chat.viewModel.state.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ChatViewModel @Inject constructor(private val repository: ChatMessageRepository) :
    ViewModel() {

    private val _chatState: MutableStateFlow<ChatState> = MutableStateFlow(ChatState.InitialState)
    val chatState = _chatState.asStateFlow()

    init {
        Log.d(TAG, "init")
        getChat()
    }

    fun getChat(){
        viewModelScope.launch {
            try {
                _chatState.update { ChatState.Loading }
                val messages = repository.getMessages()
                Log.d(TAG, "getChatMessages: $messages")
                if (messages.isEmpty()) {
                    _chatState.update { ChatState.EmptyResults }
                } else {
                    _chatState.update { ChatState.Success(messages) }
                }
            } catch (e: Exception) {
                _chatState.update {
                    ChatState.Error("${e.message}")
                }
            }
        }
    }

    companion object {
        const val TAG = "ChatViewModel"
    }
}