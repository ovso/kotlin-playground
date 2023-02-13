@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Cancel04 {
    /**
     * 코루틴 내의 연산을 취소하기 위해 Coroutine 의 확장함수인 isActive 를 사용합니다.
     * 코루틴을 취소하면 isActive 는 false 가 됩니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {
                if(System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++}")
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