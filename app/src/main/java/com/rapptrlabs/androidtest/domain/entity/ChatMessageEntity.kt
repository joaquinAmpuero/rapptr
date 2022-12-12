package com.rapptrlabs.androidtest.domain.entity

data class ChatMessageEntity(
    val userId: Int,
    val avatarUrl: String,
    val username: String,
    val message: String
)