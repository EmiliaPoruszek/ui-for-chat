package com.resi_tech.myapplication.models

data class ChatMessage(
  val author: Author,
  val message: String,
  val timestamp: Long
)