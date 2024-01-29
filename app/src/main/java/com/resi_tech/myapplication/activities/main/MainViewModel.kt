package com.resi_tech.myapplication.activities.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.resi_tech.myapplication.models.ChatMessage
import com.resi_tech.myapplication.repositories.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.launch

class MainViewModel(): ViewModel() {
  private val chatRepository = ChatRepository()

  private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
  val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

  init {
    loadMessages()
  }

  private fun loadMessages() {
    viewModelScope.launch {
      chatRepository.getMessages()
        .scan(emptyList<ChatMessage>()) { accumulator, value ->
          accumulator + value
        }
        .catch { e ->
          // TODO: Handle errors
          _messages.value = emptyList()
        }
        .collect { messageList ->
          _messages.value = messageList
        }
    }
  }
}