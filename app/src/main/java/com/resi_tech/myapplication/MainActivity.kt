package com.resi_tech.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.resi_tech.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay
import java.sql.Timestamp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Screen()
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

data class Author(
  val name: String,
  val avatarColor: Color,
  val avatarDrawableId: Int,
  val orientation: String = "left",
  val cloudColor: Color
)

data class ChatMessage(
  val author: Author,
  val message: String,
  val timestamp: Long
)

@Preview(
  showBackground = true,
  backgroundColor = 0xFFFFFFFF,
  widthDp = 360,
  heightDp = 640
)
@Composable
fun Screen() {
  val molly = Author(
    name = "Molly",
    avatarColor = Color(0xFFB8E0EF),
    orientation = "right",
    avatarDrawableId = R.drawable.molly_avatar,
    cloudColor = Color(0xFFEFB8C8)
  )
  val tom = Author(
    name = "Tom",
    avatarColor = Color(0xFFEFB8C8),
    orientation = "left",
    avatarDrawableId = R.drawable.tom_avatar,
    cloudColor = Color(0xFFB8E0EF)
  )

  val conversation = arrayListOf<ChatMessage>(
    ChatMessage(tom, "Hi", randomTime()),
    ChatMessage(molly, "Hi", randomTime()),
    ChatMessage(tom, "How are you?", randomTime()),
    ChatMessage(molly, "I'm fine, thanks!", randomTime()),
    ChatMessage(tom, "What are you doing?", randomTime()),
    ChatMessage(molly, "I'm working on a new project", randomTime()),
    ChatMessage(tom, "Cool! What is it about?", randomTime()),
    ChatMessage(molly, "It's a new app for Android", randomTime()),
  )

  MyApplicationTheme {
    Box(modifier = Modifier.fillMaxSize()) {
      LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(conversation.size) { index ->
          val message = conversation[index]
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
  Row(
    horizontalArrangement = if (author.orientation == "left") Arrangement.Start else Arrangement.End,
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth()
  ) {
    if (author.orientation == "left") {
      ImageWithPlaceholder(author.avatarDrawableId, author.avatarColor)
    }

    val authorAlign = if (author.orientation == "left") TextAlign.End else TextAlign.Start
    val textAlign = if (author.orientation == "left") TextAlign.Start else TextAlign.End

    val paddingLeft = if (author.orientation == "left") 8.dp else 0.dp
    val paddingRight = if (author.orientation == "left") 0.dp else 8.dp

    Column(
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = if (author.orientation == "left") Alignment.Start else Alignment.End,
      modifier = Modifier
        .padding(paddingLeft, 0.dp, paddingRight, 0.dp)
        .fillMaxHeight()
        .fillMaxWidth(if (author.orientation == "left") 1f else 0.8f)
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

@Composable
fun ImageWithPlaceholder(
  drawableId: Int = R.drawable.ic_launcher_foreground,
  background: Color = Color.Black
) {
  val uploaded = remember { mutableStateOf(false) }
  LaunchedEffect(Unit) {
    delay(500)
    uploaded.value = true
  }
  Column(
    verticalArrangement = Arrangement.Center,
    modifier = Modifier
      .fillMaxHeight()
      .width(50.dp)
  ) {
    if (uploaded.value) {
      LauncherImage(drawableId, background)
    } else {
      imagePlaceholder(background)
    }
  }
}

@Composable
fun LauncherImage(
  drawableId: Int = R.drawable.ic_launcher_foreground,
  background: Color = Color.Black
) {
  Box {
    Image(
      painter = painterResource(
        id = drawableId
      ),
      contentDescription = "Launcher Icon",
      modifier = Modifier
        .padding(2.dp)
        .clip(CircleShape)
        .background(background)
        .fillMaxWidth()
    )
  }
}

@Composable
fun imagePlaceholder(background: Color = Color.Black) {
  Spacer(
      modifier = Modifier
        .padding(2.dp)
        .clip(CircleShape)
        .fillMaxHeight()
        .aspectRatio(1f)
        .background(background)
        .blur(10.dp)
        .alpha(0.5f)
    )
}

var maxAgo = 60
fun randomTime(): Long {
  val random = Random(System.currentTimeMillis())
  val min = (0.7f * maxAgo).toInt()
  val max = (0.3f * maxAgo).toInt()
  val minutesAgo = random.nextInt(max) + min
  maxAgo = minutesAgo
  return System.currentTimeMillis() - minutesAgo * 60 * 1000
}

fun calculateTime(timestamp: Long): Long {
  val now = System.currentTimeMillis()
  val diff = now - timestamp
  return diff / 1000 / 60
}