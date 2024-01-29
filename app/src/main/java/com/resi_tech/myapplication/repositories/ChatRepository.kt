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

    val random = Random(System.currentTimeMillis())
    val messages = listOf(
      ChatMessage(author = Author.Molly(), message = "Hi Tom! How are you?"),
      ChatMessage(author = Author.Tom(), message = "I'm good, thanks! How are you?"),
      ChatMessage(author = Author.Molly(), message = "I'm fine, thanks!"),
      ChatMessage(author = Author.Molly(), message = "I'm working on a new project"),
      ChatMessage(author = Author.Tom(), message = "Oh! What is it about?"),
      ChatMessage(author = Author.Molly(), message = "It's a new app for Android"),
      ChatMessage(author = Author.Tom(), message = "Sounds great!"),
      ChatMessage(author = Author.Molly(), message = "Thanks!"),
      ChatMessage(author = Author.Molly(), message = "It's called Jetpack Compose"),
      ChatMessage(author = Author.Tom(), message = "Interesting... Tell me more"),
      ChatMessage(author = Author.Molly(), message = "It's a modern toolkit for building native UI"),
      ChatMessage(author = Author.Tom(), message = "Awesome!"),
      ChatMessage(author = Author.Molly(), message = "It simplifies and accelerates UI development on Android"),
    )

    // Emit each message with a delay
    messages.forEach { message ->
      emit(message)
      val duration = random.nextInt(3000) + 1000
      delay(duration.toLong())
    }
  }
}