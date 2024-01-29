package com.resi_tech.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.resi_tech.myapplication.components.ImageWithPlaceholder
import com.resi_tech.myapplication.models.Author
import com.resi_tech.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val viewModel: MainViewModel by viewModels()

    setContent {
      Screen(viewModel)
    }
  }
}

@Composable
fun AuthorName(
  text: String,
  textAlign: TextAlign,
  modifier: Modifier = Modifier
) {
  Text(
    text = "$text",
    color = Color.DarkGray,
    fontSize = 10.sp,
    textAlign = textAlign,
    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
    modifier = modifier
  )
}

@Composable
fun Ago(
  text: String,
  textAlign: TextAlign,
  modifier: Modifier = Modifier
) {
  Text(
    text = "$text",
    color = Color.DarkGray,
    fontSize = 10.sp,
    textAlign = textAlign,
    modifier = modifier
  )
}

@Composable
fun Message(
  text: String,
  textAlign: TextAlign,
  modifier: Modifier = Modifier) {
  Text(
    text = "$text",
    color = Color.Black,
    fontSize = 16.sp,
    textAlign = textAlign,
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
fun Screen(viewModel: MainViewModel = MainViewModel()) {
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
fun RoundedCard(
  color: Color = MaterialTheme.colorScheme.primary,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  val uploaded = remember { mutableStateOf(false) }
  LaunchedEffect(uploaded.value) {
    delay(100)
    uploaded.value = true
  }
  // slide the card from left or right when it is uplaoded
  ElevatedCard(
    elevation = CardDefaults.cardElevation(
      defaultElevation = 8.dp,
    ),
    modifier = modifier
      .fillMaxSize()
      .padding(16.dp, 8.dp, 16.dp, 8.dp)
      .clip(RoundedCornerShape(8.dp))
      .fillMaxWidth(0.6f),
      colors = CardDefaults.cardColors(
        containerColor = color
      ),
    ) {
    content()
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
      .padding(8.dp)
      .fillMaxWidth()
  ) {
    if (author.orientation == "left") {
      ImageWithPlaceholder(author.avatarDrawableId, author.avatarColor)
    }

    val authorAlign = if (isLeft) TextAlign.End else TextAlign.Start
    val textAlign = if (isLeft) TextAlign.Start else TextAlign.End

    val paddingLeft = if (isLeft) 8.dp else 0.dp
    val paddingRight = if (isLeft) 0.dp else 8.dp

    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = if (isLeft) Alignment.Start else Alignment.End,
      modifier = Modifier
        .padding(paddingLeft, 0.dp, paddingRight, 0.dp)
        .fillMaxHeight()
        .fillMaxWidth(if (isLeft) 1f else 0.8f)
    ) {
      AuthorName(author.name, textAlign = authorAlign, modifier = Modifier.fillMaxWidth())
      Message(message, textAlign = textAlign, modifier = Modifier.fillMaxWidth())
      Ago("${calculateTime(timestamp)} min ago", textAlign = authorAlign, modifier = Modifier.fillMaxWidth())
    }

    if (author.orientation == "right") {
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