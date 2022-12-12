package com.rapptrlabs.androidtest.data.dto

data class ChatMessageModel(
    val user_id: Int = 0,
    val avatar_url: String = "",
    val name: String = "",
    val message: String = ""
)