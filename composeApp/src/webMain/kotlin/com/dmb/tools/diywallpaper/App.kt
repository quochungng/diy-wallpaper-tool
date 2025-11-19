package com.dmb.tools.diywallpaper

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ResetTv
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.dmb.tools.diywallpaper.models.ContentSource
import com.dmb.tools.diywallpaper.models.UnitType
import com.dmb.tools.diywallpaper.models.UnitValue
import com.dmb.tools.diywallpaper.models.WallpaperElement
import com.dmb.tools.diywallpaper.models.WallpaperTransform
import com.dmb.tools.diywallpaper.views.ElementView
import com.dmb.tools.diywallpaper.views.ElementView
import com.dmb.tools.diywallpaper.views.IntInput
import com.dmb.tools.diywallpaper.views.SettingsDialog
import kotlinx.browser.localStorage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    MaterialTheme(
        colorScheme = darkColorScheme()
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            var canvasWidth by remember { mutableIntStateOf(430) }
            var canvasHeight by remember { mutableIntStateOf(650) }
            var isPlaying by remember { mutableStateOf(false) }
            var elements: List<WallpaperElement> by remember { mutableStateOf(emptyList()) }
            var showSettingsDialog by remember { mutableStateOf(false) }
            var bearerToken by remember { mutableStateOf("") }

            LaunchedEffect(Unit) {
                bearerToken = localStorage.getItem("bearer_token") ?: ""
            }

            if (showSettingsDialog) {
                SettingsDialog(
                    onDismiss = { showSettingsDialog = false },
                    onSave = { token ->
                        bearerToken = token
                        localStorage.setItem("bearer_token", token)
                        showSettingsDialog = false
                    },
                    initialToken = bearerToken
                )
            }

            Scaffold(
                modifier = Modifier.weight(1f),
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Diy Wallpaper Tool")
                        },
                        actions = {
                            IconButton(onClick = {
                                elements = elements + WallpaperElement(
                                    id = "id",
                                    index = 0,
                                    contentSource = ContentSource.Image(""),
                                    transform = WallpaperTransform(
                                        width = UnitValue.Value(0f, UnitType.DP),
                                        height = UnitValue.Value(0f, UnitType.DP),
                                        positionX = UnitValue.Value(0f, UnitType.DP),
                                        positionY = UnitValue.Value(0f, UnitType.DP),
                                        rotation = 0f,
                                        scaleX = 1f,
                                        scaleY = 1f,
                                        pivotX = UnitValue.Value(0f, UnitType.DP),
                                        pivotY = UnitValue.Value(0f, UnitType.DP),
                                    ),
                                    animations = emptyList()
                                )
                            }) {
                                Icon(Icons.Default.Add, contentDescription = "Add Element")
                            }
                            IconButton(onClick = {
                                showSettingsDialog = true
                            }) {
                                Icon(Icons.Default.Settings, contentDescription = "Settings")
                            }
                        }
                    )
                },
                bottomBar = {
                    Row(
                        modifier = Modifier.padding(vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.width(16.dp))
                        Button(onClick = {}) {
                            Text("Save")
                        }
                        Spacer(Modifier.width(16.dp))
                        TextButton(onClick = {}) {
                            Text("Reset")
                        }
                    }
                }
            ) { paddingValues ->
                val density = LocalDensity.current.density

                LazyColumn(modifier = Modifier.padding(paddingValues)) {
                    item {
                        Row {
                            Spacer(modifier = Modifier.width(16.dp))
                            IntInput(
                                label = {
                                    Text("Canvas Width")
                                },
                                value = canvasWidth,
                                onValueChange = {
                                    canvasWidth = it
                                }
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            IntInput(
                                label = {
                                    Text("Canvas Height")
                                },
                                value = canvasHeight,
                                onValueChange = {
                                    canvasHeight = it
                                }
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = "Density: $density",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    item { Spacer(Modifier.height(16.dp)) }
                    item {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Wallpaper Elements"
                        )
                    }
                    itemsIndexed(elements) { index, element ->
                        ElementView(
                            modifier = Modifier.fillMaxWidth(),
                            element = element,
                            onDelete = {
                                val newList = elements.toMutableList()
                                newList.removeAt(index)
                                elements = newList
                            },
                            onChange = {
                                elements = elements.mapIndexed { i, e ->
                                    if (i == index) {
                                        it
                                    } else {
                                        e
                                    }
                                }
                            }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .width(500.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                val painter = rememberAsyncImagePainter("https://picsum.photos/300/300")
                Canvas(
                    modifier = Modifier.size(width = canvasWidth.dp, height = canvasHeight.dp)
                ) {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(0f, 0f),
                        size = Size(canvasWidth * density, canvasHeight * density)
                    )
                    drawRect(
                        color = Color.Red,
                        topLeft = Offset(10f, 10f),
                        size = Size(20f, 20f)
                    )
                    with(painter) {
                        draw(painter.intrinsicSize)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Slider(
                        modifier = Modifier.weight(1f),
                        value = 1f,
                        valueRange = 1f..10f,
                        onValueChange = {

                        }
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    FloatingActionButton(
                        modifier = Modifier.size(56.dp),
                        onClick = {
                            isPlaying = !isPlaying
                        },
                        content = {
                            Icon(
                                imageVector = if (isPlaying) {
                                    Icons.Default.Pause
                                } else {
                                    Icons.Default.PlayArrow
                                },
                                contentDescription = ""
                            )
                        }
                    )
                }
                Spacer(Modifier.height(16.dp))
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}