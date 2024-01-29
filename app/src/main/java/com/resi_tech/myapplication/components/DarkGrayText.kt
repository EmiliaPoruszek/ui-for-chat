package com.resi_tech.myapplication.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DarkGrayText(
  text: String,
  textAlign: TextAlign,
  style: TextStyle = MaterialTheme.typography.bodySmall,
  modifier: Modifier = Modifier
) {
  Text(
    text = "$text",
    color = Color.DarkGray,
    textAlign = textAlign,
    style = style,
    modifier = modifier
  )
}