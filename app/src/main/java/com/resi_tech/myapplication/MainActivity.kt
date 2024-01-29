package com.resi_tech.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.resi_tech.myapplication.components.ImageWithPlaceholder
import com.resi_tech.myapplication.components.RoundedCard
import com.resi_tech.myapplication.models.Author
import com.resi_tech.myapplication.ui.theme.DimenScheme
import com.resi_tech.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: MainViewModel by viewModels()

    setContent {
      ChatScreen(viewModel)
    }
  }
}

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

@Preview(
  showBackground = true,
  backgroundColor = 0xFFFFFFFF,
  widthDp = 360,
  heightDp = 640
)
@Composable
fun ChatScreen(viewModel: MainViewModel = MainViewModel()) {
  val messages by viewModel.messages.collectAsState(initial = emptyList())

  MyApplicationTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(messages.size) { index ->
          val message = messages[index]
          ChatItem(
            author = message.author,
            message = message.message,
            timestamp = message.timestamp
          )
        }
      }
    }
  }
}

@Composable
fun ChatItem(
  author: Author,
  message: String,
  timestamp: Long
) {
  Box(
    contentAlignment = if (author.orientation == "left") Alignment.CenterStart else Alignment.CenterEnd,
    modifier = Modifier.fillMaxWidth()
  ) {
    RoundedCard(
      color = author.cloudColor,
      modifier = Modifier
        .width(300.dp)
    ) {
      ChatItemContent(
        author,
        message,
        timestamp
      )
    }
  }
}



@Composable
fun ChatItemContent(
  author: Author,
  message: String = "Hi",
  timestamp: Long) {
  val isLeft = author.orientation == "left"
  Row(
    horizontalArrangement = if (isLeft) Arrangement.Start else Arrangement.End,
    modifier = Modifier
      .padding(DimenScheme.Medium)
      .fillMaxWidth()
  ) {
    if (isLeft) {
      ImageWithPlaceholder(author.avatarDrawableId, author.avatarColor)
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
      DarkGrayText(author.name, textAlign = authorAlign, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.titleSmall)
      DarkGrayText(message, textAlign = textAlign, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.bodyLarge)
      DarkGrayText("${calculateTime(timestamp)} min ago", textAlign = authorAlign, modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.labelSmall)
    }

    if (!isLeft) {
      ImageWithPlaceholder(author.avatarDrawableId, author.avatarColor)
    }
  }
}

/*var maxAgo = 60
fun randomTime(): Long {
  val random = Random(System.currentTimeMillis())
  val min = (0.7f * maxAgo).toInt()
  val max = (0.3f * maxAgo).toInt()
  val minutesAgo = random.nextInt(max) + min
  maxAgo = minutesAgo
  return System.currentTimeMillis() - minutesAgo * 60 * 1000
}*/

fun calculateTime(timestamp: Long): Long {
  val now = System.currentTimeMillis()
  val diff = now - timestamp
  return diff / 1000 / 60
}