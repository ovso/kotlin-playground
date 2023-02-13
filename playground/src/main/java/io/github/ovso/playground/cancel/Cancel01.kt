@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.cancel

import kotlinx.coroutines.*

object Cancel01 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val job = launch {
            repeat(1000) {
                println("job: I'm sleeping $it ...")
                delay(500L)
            }
        }
        delay(1300L) // 잠시 지연
        println("main: I'm tired of waiting!")
        job.cancel() // 작업을 취소합니다.
        job.join() // 작업 완료를 기다립니다.
        // job.cancelAndJoin() // 작업을 취소하고 완료를 기다립니다.
        println("main: Now I can quit.")
    }
}