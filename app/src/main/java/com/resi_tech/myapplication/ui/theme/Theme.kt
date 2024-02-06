package com.resi_tech.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
  primary = Purple40,
  secondary = Pink80,
  tertiary = Pink40,
  background = BackgroundDark,

  /*surface = Color.Transparent,
  onPrimary = Color.Transparent,
  onSecondary = Color.White,
  onTertiary = Color.Transparent,
  onBackground = Color.White,
  onSurface = Color.White,*/
)

val LightColorScheme = lightColorScheme(
  primary = Purple80,
  secondary = PurpleDark,
  tertiary = Pink80,
  background = BackgroundLight,

  /*surface = Color.Transparent,
  onPrimary = Color.Transparent,
  onSecondary = Color.White,
  onTertiary = Color.Transparent,
  onBackground = Color.Transparent,
  onSurface = Color.Transparent,*/

)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colorScheme = when {
    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
      Log.d("MyApplication", "Dynamic color scheme")
      val context = LocalContext.current
      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    darkTheme -> {
      Log.d("MyApplication", "Dark color scheme")
      DarkColorScheme
    }

    else -> {
      Log.d("MyApplication", "Light color scheme")
      LightColorScheme
    }
  }
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = colorScheme.primary.toArgb()
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
    }
  }

  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

object DynamicColorManager {
  var colorScheme by mutableStateOf<ColorScheme>(LightColorScheme)
}