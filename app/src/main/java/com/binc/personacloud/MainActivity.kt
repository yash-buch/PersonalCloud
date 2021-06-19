package com.binc.personacloud

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.binc.personacloud.ui.theme.PersonaCloudTheme
import com.binc.personalcloud.core.entity.ILogger
import com.binc.personalcloud.core.interactors.IDataRepository
import com.binc.personalcloud.core.usecases.SaveMediaUC
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonaCloudTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
        class repo : IDataRepository
        class logger: ILogger {
            override fun info(tag: String, message: String?) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show() }
            }

            override fun debug(tag: String, message: String?) {
                TODO("Not yet implemented")
            }

            override fun warning(tag: String, message: String?) {
                TODO("Not yet implemented")
            }

            override fun error(tag: String, message: String?) {
                TODO("Not yet implemented")
            }
        }

        val us = SaveMediaUC(Dispatchers.Main, repo(), logger())
        CoroutineScope(Dispatchers.Default).launch {
            us.saveMedia("path")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PersonaCloudTheme {
        Greeting("Android")
    }
}