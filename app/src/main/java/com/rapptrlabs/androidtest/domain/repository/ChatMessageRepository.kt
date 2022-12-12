package com.rapptrlabs.androidtest.domain.repository

import com.rapptrlabs.androidtest.domain.entity.ChatMessageEntity

interface ChatMessageRepository {
    suspend fun getMessages(): List<ChatMessageEntity>
}