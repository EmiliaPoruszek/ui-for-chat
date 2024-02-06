package com.resi_tech.myapplication.models

data class ChatMessage(
  val id: String,
  val author: Author,
  val message: String,
  val timestamp: Long = System.currentTimeMillis()
)