package com.thanhnhat.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { BasicsApp() } }
    }
}

private val lessons = listOf("Happy Birthday", "Compose Article", "Task Manager", "Compose Quadrant")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicsApp() {
    var lesson by rememberSaveable { mutableIntStateOf(-1) }
    BackHandler(lesson >= 0) { lesson = -1 }
    Scaffold(topBar = {
        TopAppBar(title = { Text(if (lesson < 0) "Lab 1 · Bốn bài cơ bản" else lessons[lesson]) },
            navigationIcon = { if (lesson >= 0) TextButton(onClick = { lesson = -1 }) { Text("‹ Về") } })
    }) { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            when (lesson) {
                0 -> Birthday()
                1 -> Article()
                2 -> TaskManager()
                3 -> Quadrants()
                else -> Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Text("Làm quen với Text, Image, Row, Column, Box và Modifier.", style = MaterialTheme.typography.bodyLarge)
                    lessons.forEachIndexed { i, title ->
                        ElevatedCard(onClick = { lesson = i }, modifier = Modifier.fillMaxWidth()) {
                            Text("${i + 1}. $title", Modifier.padding(24.dp), style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Birthday() {
    Column(Modifier.fillMaxSize().background(Color(0xFFEADDFF)).padding(24.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🎂", fontSize = 100.sp)
        Text("Happy Birthday\nThanh Nhật!", fontSize = 48.sp, lineHeight = 56.sp, textAlign = TextAlign.Center)
        Text("From your friends", Modifier.align(Alignment.End).padding(top = 24.dp), fontSize = 22.sp)
    }
}

@Composable
private fun Article() {
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Box(Modifier.fillMaxWidth().height(180.dp).background(Color(0xFF073042)), contentAlignment = Alignment.Center) {
            Text("< Jetpack Compose />", color = Color(0xFF3DDC84), fontSize = 26.sp, fontWeight = FontWeight.Bold)
        }
        Text("Jetpack Compose tutorial", Modifier.padding(16.dp), style = MaterialTheme.typography.headlineSmall)
        Text("Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            Modifier.padding(horizontal = 16.dp), textAlign = TextAlign.Justify)
        Text("In this tutorial, you build a simple UI component with declarative functions. You call composable functions to describe what the interface should look like. Compose updates the UI when the application state changes. Use Row and Column to arrange elements, and Modifier to set their size, spacing, and appearance.",
            Modifier.padding(16.dp), textAlign = TextAlign.Justify)
    }
}

@Composable
private fun TaskManager() {
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Icon(Icons.Default.CheckCircle, "Hoàn thành", Modifier.size(110.dp), tint = Color(0xFF198754))
        Text("All tasks completed", Modifier.padding(top = 24.dp, bottom = 8.dp), fontWeight = FontWeight.Bold)
        Text("Nice work!", fontSize = 16.sp)
    }
}

@Composable
private fun Quadrants() {
    Column(Modifier.fillMaxSize()) {
        Row(Modifier.weight(1f)) {
            Quadrant("Text composable", "Displays text and follows the recommended Material Design guidelines.", Color(0xFFEADDFF), Modifier.weight(1f).fillMaxHeight())
            Quadrant("Image composable", "Creates a composable that lays out and draws a given image.", Color(0xFFD0BCFF), Modifier.weight(1f).fillMaxHeight())
        }
        Row(Modifier.weight(1f)) {
            Quadrant("Row composable", "Places its children in a horizontal sequence.", Color(0xFFB69DF8), Modifier.weight(1f).fillMaxHeight())
            Quadrant("Column composable", "Places its children in a vertical sequence.", Color(0xFFF6EDFF), Modifier.weight(1f).fillMaxHeight())
        }
    }
}

@Composable
private fun Quadrant(title: String, body: String, color: Color, modifier: Modifier) {
    Column(modifier.background(color).verticalScroll(rememberScrollState()).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        Text(body, textAlign = TextAlign.Justify)
    }
}
