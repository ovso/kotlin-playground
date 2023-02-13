package io.github.ovso.playground

import kotlinx.coroutines.*

object Basic03 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        doWorld()
    }

    private suspend fun doWorld() = coroutineScope {  // this: CoroutineScope
        launch {
            delay(1000L)
            println("World!")
        }
        println("Hello")
    }
}