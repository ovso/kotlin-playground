@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

object Compose04 {
    /*
    somethingUsefulOneAsync, somethingUsefulTowAsync 함수는 suspending(일시 중단) 함수가 아닙니다.
    runBlocking 외부에서 실행된다는 것을 보면 알 수 있습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val time = measureTimeMillis {
            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTowAsync()

            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }

        println("Completed in $time ms")
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun somethingUsefulTowAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
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