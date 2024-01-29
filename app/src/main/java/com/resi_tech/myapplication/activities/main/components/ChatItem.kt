package com.resi_tech.myapplication.activities.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.resi_tech.myapplication.R
import com.resi_tech.myapplication.activities.main.DarkGrayText
import com.resi_tech.myapplication.components.ImageWithPlaceholder
import com.resi_tech.myapplication.components.RoundedCard
import com.resi_tech.myapplication.ui.theme.DimenScheme

@Composable
fun ChatItem(
  authorName: String = "Steve",
  avatarDrawableId: Int = R.drawable.ic_launcher_foreground,
  avatarColor: Color = Color.Black,
  cloudColor: Color = Color.White,
  message: String = "Hi",
  timestamp: Long = System.currentTimeMillis(),
  isLeft: Boolean = false,
  modifier: Modifier = Modifier
) {
  Box(
    contentAlignment = if (isLeft) Alignment.CenterStart else Alignment.CenterEnd,
    modifier = modifier
  ) {
    RoundedCard(
      color = cloudColor,
      modifier = Modifier
        .width(300.dp)
    ) {
      ChatItemContent(
        authorName = authorName,
        avatarDrawableId = avatarDrawableId,
        avatarColor = avatarColor,
        message = message,
        timestamp = timestamp,
        isLeft = isLeft
      )
    }
  }
}



@Composable
fun ChatItemContent(
  authorName: String = "Steve",
  avatarDrawableId: Int = R.drawable.ic_launcher_foreground,
  avatarColor: Color = Color.Black,
  message: String = "Hi",
  timestamp: Long = System.currentTimeMillis(),
  isLeft: Boolean = false
) {
  Row(
    horizontalArrangement = if (isLeft) Arrangement.Start else Arrangement.End,
    modifier = Modifier
      .padding(DimenScheme.Medium)
      .fillMaxWidth()
  ) {
    if (isLeft) {
      ImageWithPlaceholder(avatarDrawableId, avatarColor)
    }

    val authorAlign = if (isLeft) TextAlign.End else TextAlign.Start
    val textAlign = if (isLeft) TextAlign.Start else TextAlign.End

    val paddingLeft = if (isLeft) DimenScheme.Medium else DimenScheme.None
    val paddingRight = if (isLeft) DimenScheme.None else DimenScheme.Medium

    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = if (isLeft) Alignment.Start else Alignment.End,
      modifier = Modifier
        .padding(paddingLeft, DimenScheme.None, paddingRight, DimenScheme.None)
        .fillMaxHeight()
        .fillMaxWidth(if (isLeft) 1f else 0.8f)
    ) {
      DarkGrayText(
        authorName,
        textAlign = authorAlign,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.titleSmall
      )

      DarkGrayText(
        message,
        textAlign = textAlign,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge
      )

      DarkGrayText(
        "${calculateMinutesAgo(timestamp)} min ago",
        textAlign = authorAlign,
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.labelSmall
      )
    }

    if (!isLeft) {
      ImageWithPlaceholder(avatarDrawableId, avatarColor)
    }
  }
}

private fun calculateMinutesAgo(timestamp: Long): Long {
  val now = System.currentTimeMillis()
  val diff = now - timestamp
  return diff / 1000 / 60
}