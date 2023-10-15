package ru.thever4.lessons.lesson4_2

import kotlin.random.Random

object ChatDataStorage {
    private const val MINUTE = 60L
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR
    private const val SECOND_IN_MS = 1000L
    private const val THIS_PERSON_DOES_NOT_EXISTS =
        "https://thispersondoesnotexist.com/"

    val userChats = (0..25).map {
        ChatItem(
            userAvatar = THIS_PERSON_DOES_NOT_EXISTS,
            userName = generateRandomUsername(),
            message = generateRandomMessage(),
            messagesCount = generateSmallOrBigInt(),
            receivedTime = generateRandomTimestamp(),
        )
    }

    private fun generateRandomWord() =
        (0..Random.nextInt(4, 8))
            .map { Random.nextInt('a'.code, 'z'.code).toChar() }
            .joinToString(separator = "")

    private fun generateRandomSentence(limit: Int) =
        (1..Random.nextInt(1, limit))
            .joinToString(separator = " ") { generateRandomWord() }

    private fun generateRandomUsername() =
        generateRandomSentence(3)

    private fun generateRandomMessage() =
        generateRandomSentence(25)

    private fun generateRandomTimestamp() =
        (System.currentTimeMillis() / SECOND_IN_MS) - Random.nextLong(0, DAY)

    private fun generateSmallOrBigInt() =
        Random.nextInt(1, if (Random.nextBoolean()) 9 else 999)
}
