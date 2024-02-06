import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.resi_tech.myapplication.ui.theme.DarkColorScheme
import com.resi_tech.myapplication.ui.theme.DimenScheme
import com.resi_tech.myapplication.ui.theme.DynamicColorManager
import com.resi_tech.myapplication.ui.theme.LightColorScheme
import com.resi_tech.myapplication.ui.theme.PurpleDark

@Preview(
  showBackground = true,
  widthDp = 360,
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenteredTopAppBar(
  content: @Composable (PaddingValues) -> Unit = {}
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

  var checked by remember { mutableStateOf(false) }

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

    topBar = {
      CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = DynamicColorManager.colorScheme.primary,
          titleContentColor = DynamicColorManager.colorScheme.secondary,
          actionIconContentColor = DynamicColorManager.colorScheme.secondary,
          navigationIconContentColor = DynamicColorManager.colorScheme.secondary,
        ),
        title = {
          Text(
            "Your chat",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        },
        navigationIcon = {
          IconButton(onClick = { /* do something */ }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "Localized description"
            )
          }
        },
        actions = {
          Switch(
            checked = checked,
            onCheckedChange = {
              DynamicColorManager.colorScheme = if (it) DarkColorScheme else LightColorScheme
              checked = it
            },
            colors = SwitchDefaults.colors(
              checkedThumbColor = PurpleDark,
              checkedTrackColor = DynamicColorManager.colorScheme.primary,
              checkedBorderColor = DynamicColorManager.colorScheme.secondary,
              uncheckedThumbColor = PurpleDark,
              uncheckedTrackColor = DynamicColorManager.colorScheme.primary,
              uncheckedBorderColor = DynamicColorManager.colorScheme.secondary,
            ),
            thumbContent = if (checked) {
              { Text("🌙") }
            } else {
              { Text("☀️") }
            },
            modifier = Modifier.padding(DimenScheme.Medium)
          )
        },
        scrollBehavior = scrollBehavior,
      )
    },
  ) { innerPadding ->
    content(innerPadding)
  }
}