package com.resi_tech.myapplication.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.resi_tech.myapplication.ui.theme.DimenScheme
import com.resi_tech.myapplication.ui.theme.DynamicColorManager

@Composable
fun RoundedCard(
  color: Color = DynamicColorManager.colorScheme.primary,
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  ElevatedCard(
    elevation = CardDefaults.cardElevation(
      defaultElevation = DimenScheme.Medium,
    ),
    modifier = modifier
      .padding(
        DimenScheme.Large,
        DimenScheme.Medium,
        DimenScheme.Large,
        DimenScheme.Medium
      )
      .clip(RoundedCornerShape(DimenScheme.RadiusMedium)),
    colors = CardDefaults.cardColors(
      containerColor = color
    ),
  ) {
    content()
  }
}