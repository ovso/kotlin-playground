@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

object Cancel11 {
    /*
     1000ms 가 소요되는 일시중단 함수를 2개를 순차 처리 하기 때문에 연상을 수행하는 데 2000ms 을 초과 합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        Thread.currentThread().name.also {
            println(it)
        }
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }
}