@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Cancel02 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            println(Thread.currentThread().name) // 쓰레드 이름을 출력합니다.
            while (i < 5) { // CPU  낭비하는 반복문 입니다.
                // 1 초에 메시지를 두 번출력합니다.
                if(System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 취소하고 완료를 기다리고 있으나 취소되지 않습니다.
        println("main: Now I can quit.")
    }
}