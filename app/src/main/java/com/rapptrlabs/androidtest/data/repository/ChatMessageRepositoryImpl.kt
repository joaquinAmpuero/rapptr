package com.rapptrlabs.androidtest.data.repository

import com.rapptrlabs.androidtest.data.remote.ApiInterface
import com.rapptrlabs.androidtest.domain.entity.ChatMessageEntity
import com.rapptrlabs.androidtest.domain.repository.ChatMessageRepository
import javax.inject.Inject

class ChatMessageRepositoryImpl @Inject constructor(private val api: ApiInterface) :
    ChatMessageRepository {
    override suspend fun getMessages(): List<ChatMessageEntity> {
        return api.getRetailerPoints().data.map { ChatMessageEntity(
            userId = it.user_id,
            message = it.message,
            username = it.name,
            avatarUrl = it.avatar_url,
        ) }
    }
}