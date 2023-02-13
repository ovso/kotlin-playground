package io.github.ovso.playground.basic

import kotlinx.coroutines.*

object Basic05 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val job = launch {
            delay(1000L)
            println("World!")
        }
        println("Hello")
        job.join() // 코루틴이 완료될 때까지 대기합니다.
        // job.cancelAndJoin() // 코루틴이 취소될 때까지 대기합니다.
        println("Done")
    }

}