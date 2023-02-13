@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.compose

import kotlinx.coroutines.*

object Compose06 {
    // 취소는 항상 코루틴 계층 구조를 통해 전파됩니다.
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        try {
            failedConcurrentSume()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    private suspend fun failedConcurrentSume(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(Long.MAX_VALUE)
                42
            } finally {
                println("First child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }

}