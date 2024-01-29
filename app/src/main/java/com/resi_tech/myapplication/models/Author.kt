package com.resi_tech.myapplication.models

import androidx.compose.ui.graphics.Color
import com.resi_tech.myapplication.R

sealed class Author(
  val name: String,
  val avatarColor: Color,
  val avatarDrawableId: Int,
  val orientation: String = "left",
  val cloudColor: Color
) {
  class Molly(): Author("Molly", Color(0xFFB8E0EF), R.drawable.molly_avatar,"right", Color(0xFFEFB8C8))

  class Tom(): Author("Tom", Color(0xFFEFB8C8), R.drawable.tom_avatar, "left", Color(0xFFB8E0EF))
}