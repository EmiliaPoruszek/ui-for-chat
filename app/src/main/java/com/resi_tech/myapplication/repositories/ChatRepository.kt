package com.resi_tech.myapplication.repositories

import com.resi_tech.myapplication.models.Author
import com.resi_tech.myapplication.models.ChatMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class ChatRepository {
  suspend fun getMessages(): Flow<ChatMessage> = flow {
    // pretend we wait for a network response
    delay(3000)

    val now = System.currentTimeMillis()
    val random = Random(System.currentTimeMillis())
    val messages = listOf(
      ChatMessage(id = "A1", author = Author.Molly(), message = "Hi Tom! How are you?", timestamp = now - 15 * 60 * 1000),
      ChatMessage(id = "A2", author = Author.Tom(), message = "I'm good, thanks! How are you?", timestamp = now - 14 * 60 * 1000),
      ChatMessage(id = "A3", author = Author.Molly(), message = "I'm fine, thanks!", timestamp = now - 13 * 60 * 1000),
      ChatMessage(id = "A4", author = Author.Molly(), message = "I'm working on a new project", timestamp = now - 11 * 60 * 1000),
      ChatMessage(id = "A5", author = Author.Tom(), message = "Oh! What is it about?", timestamp = now - 10 * 60 * 1000),
      ChatMessage(id = "A6", author = Author.Molly(), message = "It's a new app for Android", timestamp = now - 9 * 60 * 1000),
      ChatMessage(id = "A7", author = Author.Tom(), message = "Sounds great!", timestamp = now - 8 * 60 * 1000),
      ChatMessage(id = "A8", author = Author.Molly(), message = "Thanks!", timestamp = now - 6 * 60 * 1000),
      ChatMessage(id = "A9", author = Author.Molly(), message = "It's called Jetpack Compose", timestamp = now - 4 * 60 * 1000),
      ChatMessage(id = "A10", author = Author.Tom(), message = "Interesting... Tell me more", timestamp = now - 4 * 60 * 1000),
      ChatMessage(id = "A11", author = Author.Molly(), message = "It's a modern toolkit for building native UI", timestamp = now - 2 * 60 * 1000),
      ChatMessage(id = "A12", author = Author.Tom(), message = "Awesome!", timestamp = now - 1 * 60 * 1000),
      ChatMessage(id = "A13", author = Author.Molly(), message = "It simplifies and accelerates UI development on Android", timestamp = now - 1 * 60 * 1000),
      ChatMessage(id = "A14", author = Author.Tom(), message = "I can't wait to try it!", timestamp = now),
      ChatMessage(id = "A15", author = Author.Molly(), message = "You should! It's available in alpha now", timestamp = now),
      ChatMessage(id = "A16", author = Author.Tom(), message = "Nice! I'll check it out", timestamp = now),
      ChatMessage(id = "A17", author = Author.Molly(), message = "Let me know what you think!", timestamp = now),
      ChatMessage(id = "A18", author = Author.Tom(), message = "Sure!", timestamp = now),
    )

    // Emit each message with a delay
    messages.forEach { message ->
      emit(message)
      val duration = random.nextInt(3000) + 1000
      delay(duration.toLong())
    }
  }
}