package io.github.ovso.playground.basic

import kotlinx.coroutines.*

object Basic01 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        launch {
            delay(1000L)
            println("World!")
        }
        println("Hello")
    }
}