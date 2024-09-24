package com.example.piano2025

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.piano2025.ui.theme.Piano2025Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Piano2025Theme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text("App") }
                    )
                }, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Piano()
}

@Composable
fun Piano() {

    Column (Modifier.fillMaxSize().background(Color.Black)){
        Box(Modifier.weight(0.5f).fillMaxWidth().background(Color.Black), contentAlignment = Alignment.TopEnd){
            Image(
                painter = painterResource(id = R.drawable.img), // Nombre del recurso en res/drawable
                contentDescription = "Descripción de la imagen",
                modifier = Modifier.fillMaxSize(), // Modificador para ajustar el tamaño
                contentScale = ContentScale.Crop // Cómo escalar la imagen dentro del contenedor
            )

        }
        Teclas(Modifier.weight(1f))
    }



}

@Composable
fun Teclas(modifier: Modifier) {
    Row(
        modifier
            .fillMaxSize()
            .padding(40.dp)
            .background(Color.Gray)
    ) {


        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.doo, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.re, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.mi, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.fa, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.sol, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.la, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
        Tecla(R.raw.si, Modifier.weight(1f))
        Spacer(Modifier.width(1.dp))
    }
}

@Composable
fun Tecla(nota: Int, modifier: Modifier) {
    var suena by remember { mutableStateOf(false) } // Estado para controlar cuándo mostrar un Composable

    Box(
        modifier = modifier
            .padding(20.dp)
            .fillMaxHeight()
            .background(color = Color.White)
            .clickable { suena = true })
    {
        if (suena) {
            Play(nota)
            suena = false
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Piano2025Theme {
        Greeting("Android")
    }
}


@Composable
fun Play(nota: Int) {
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? = remember { null }
    if (mediaPlayer == null) {
        // Inicializar MediaPlayer con el archivo en res/raw
        mediaPlayer = MediaPlayer.create(LocalContext.current, nota)
        mediaPlayer?.setVolume(1f,1f)
        Log.d("MyComposable", "Pulsado")

    }
    if (isPlaying) {
        mediaPlayer?.pause()
    } else {
        mediaPlayer?.start()
    }
    isPlaying = !isPlaying

}