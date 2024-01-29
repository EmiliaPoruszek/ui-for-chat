package com.resi_tech.myapplication.activities.main

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
import com.resi_tech.myapplication.activities.main.components.ChatItem
import com.resi_tech.myapplication.components.DarkGrayText
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
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier.fillMaxSize()
    ) {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(messages.size) { index ->
          val message = messages[index]
          ChatItem(
            authorName = message.author.name,
            avatarDrawableId = message.author.avatarDrawableId,
            avatarColor = message.author.avatarColor,
            cloudColor = message.author.cloudColor,
            message = message.message,
            timestamp = message.timestamp,
            isLeft = message.author.orientation == "left",
            modifier = Modifier.fillMaxWidth()
          )
        }
      }
      if (messages.isEmpty()) {
          DarkGrayText(
            text = "No messages yet",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
          )
      }
    }
  }
}