package com.resi_tech.myapplication.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.resi_tech.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun ImageWithPlaceholder(
  drawableId: Int = R.drawable.ic_launcher_foreground,
  background: Color = Color.Black
) {
  // pretend image is uploading
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
      Image(drawableId, background)
    } else {
      ImagePlaceholder(background)
    }
  }
}

@Composable
fun Image(
  drawableId: Int = R.drawable.ic_launcher_foreground,
  background: Color = Color.Black
) {
  val visible = remember { mutableStateOf(true) }
  Box {
    if (visible.value) {
      Image(
        painter = painterResource(
          id = drawableId
        ),
        contentDescription = "User avatar",
        modifier = Modifier
          .padding(2.dp)
          .clip(CircleShape)
          .background(background)
          .fillMaxWidth()
          .clickable { visible.value = !visible.value }
      )
    } else {
      ImagePlaceholder(background, Modifier.clickable { visible.value = !visible.value })
    }
  }
}

@Composable
fun ImagePlaceholder(background: Color = Color.Black, modifier: Modifier = Modifier) {
  Spacer(
    modifier = modifier
      .padding(2.dp)
      .clip(CircleShape)
      .fillMaxHeight()
      .aspectRatio(1f)
      .background(background)
      .blur(10.dp)
      .alpha(0.5f)
  )
}