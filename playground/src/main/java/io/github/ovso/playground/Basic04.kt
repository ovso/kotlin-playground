package io.github.ovso.playground

import kotlinx.coroutines.*

object Basic04 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        doWorld() // 코루틴 안의 코루틴이 완료될 때까지 대기합니다.
        println("Done")
    }

    private suspend fun doWorld() = coroutineScope {
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }
}