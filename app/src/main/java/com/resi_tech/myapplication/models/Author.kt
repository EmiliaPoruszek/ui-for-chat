package com.resi_tech.myapplication.models

import androidx.compose.ui.graphics.Color

data class Author(
  val name: String,
  val avatarColor: Color,
  val avatarDrawableId: Int,
  val orientation: String = "left",
  val cloudColor: Color
)
