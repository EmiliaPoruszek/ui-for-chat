package com.resi_tech.myapplication.activities.main.components

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import com.resi_tech.myapplication.R
import com.resi_tech.myapplication.components.DarkGrayText
import com.resi_tech.myapplication.components.ImageWithPlaceholder
import com.resi_tech.myapplication.components.RoundedCard
import com.resi_tech.myapplication.ui.theme.DimenScheme
import kotlinx.coroutines.delay

@Preview(
  showBackground = true,
  backgroundColor = 0x666666,
  widthDp = 360,
  heightDp = 120
)
@Composable
fun ChatItem(
  authorName: String = "Steve",
  avatarDrawableId: Int = R.drawable.ic_launcher_foreground,
  avatarColor: Color = Color.Black,
  cloudColor: Color = Color.White,
  message: String = "Hi! How are you?",
  timestamp: Long = System.currentTimeMillis(),
  isLeft: Boolean = false,
  modifier: Modifier = Modifier
) {
  val uploaded = remember { mutableStateOf(false) }
  LaunchedEffect(Unit) {
    delay(500)
    uploaded.value = true
  }
  val offset by animateIntOffsetAsState(
    targetValue = if (!uploaded.value) {
      if (isLeft) {
        IntOffset(-300, 0)
      } else {
        IntOffset(300, 0)
      }
    } else {
      IntOffset.Zero
    },
    label = "offset"
  )
  Box(
    contentAlignment = if (isLeft) Alignment.CenterStart else Alignment.CenterEnd,
    modifier = modifier
  ) {
    RoundedCard(
      color = cloudColor,
      modifier = Modifier
        .fillMaxWidth(0.7f)
        .offset { offset }
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

    val textAlign = if (isLeft) TextAlign.Start else TextAlign.End
    val padding = if (isLeft) DimenScheme.Medium else DimenScheme.None

    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = if (isLeft) Alignment.Start else Alignment.End,
      modifier = Modifier
        .padding(padding, DimenScheme.None, padding, DimenScheme.None)
        .fillMaxHeight()
        .fillMaxWidth(if (isLeft) 1f else 0.8f)
    ) {
      DarkGrayText(
        authorName,
        textAlign = textAlign,
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
        textAlign = textAlign,
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