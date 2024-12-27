package com.example.practica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica.ui.theme.PracticaTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.coerceAtLeast
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.widget.Button
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaTheme {
                MyApp(Modifier.fillMaxSize())
            }
        }
    }
}


@Preview(showBackground = true, name = "previa")
@Composable
fun GreetingPreview() {
    Greeting("Android")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(),
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            modifier
                .padding(10.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row() {
                Column(
                    modifier
                        .weight(1f)
                ) {
                    Text(
                        text = "Hola",
                    )
                    Text(
                        text = "$name!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (!expanded) stringResource(R.string.show_more) else stringResource(
                            R.string.show_less
                        )
                    )
                }
            }

            if (expanded)
                Text(
                    "lorem ipsum lorem ipsumlorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsumlorem ipsum",
                    modifier.fillMaxWidth()
                )
        }


    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier, lista: List<String> = listOf("Mundo", "Alex")) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(vertical = 4.dp)
    )
    {
        items(lista) { elem ->
            Greeting(elem)
        }
    }

}

@Composable
fun MainScreen(modifier: Modifier = Modifier, onContinue: () -> Unit) {

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Bienvenido!",
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.secondary
            )
        )
        Spacer(modifier.height(20.dp))
        ElevatedButton(
            onClick = onContinue,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Continuar")
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val lotInfo: List<String> = List(100) { "$it" }

    var showOnboardingScreen by remember { mutableStateOf(true) }
    if (showOnboardingScreen)
        MainScreen(onContinue = { showOnboardingScreen = false })
    else
        Greetings(Modifier, lotInfo)
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DarkMode"
)
@Preview(
    showBackground = true,
    name = "My app",
)
@Composable
fun MyAppPreview() {
    MyApp(modifier = Modifier)
}

