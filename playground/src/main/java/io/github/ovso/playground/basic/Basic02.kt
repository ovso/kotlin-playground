package io.github.ovso.playground.basic

import kotlinx.coroutines.*

object Basic02 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        launch { doWorld() }
        println("Hello")
    }

    private suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }
}