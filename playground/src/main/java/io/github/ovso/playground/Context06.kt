@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Context06 {
    /*
    다른 Job 객체가, 새로운 코루틴의 컨텍스트로 전달되면 부모의 Job 객체를 재정의합니다.
    따라서, 새로운 코루틴은 부모의 코루틴 범위를 벗어나게 되면서 독립적으로 실행됩니다.
    이 새로운 코루틴은 부모의 Job 을 취소해도 취소되지 않습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // launch a coroutine to process some kind of incoming request
        val request = launch {
            // it spawns two other jobs
            launch(Job()) {
                println("job1: I run in my own Job and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            // and the other inherits the parent context
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            }
        }
        delay(500)
        request.cancel() // cancel processing of the request
        println("main: Who has survived request cancellation?")
        delay(1000) // delay the main thread for a second to see what happens
    }
}