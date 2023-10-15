package ru.thever4.lessons.lesson4_2

data class ChatItem(
    val userAvatar: String,
    val userName: String,
    val message: String,
    val messagesCount: Int,
    val receivedTime: Long,
)
