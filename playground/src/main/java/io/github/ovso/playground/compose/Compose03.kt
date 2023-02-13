@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.compose

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

object Compose03 {
    /*
    async(start = CoroutineStart.LAZY)  는 async{..} 와는 다르다.
    start() 함수를 호출해야만 suspend 기능을 활성화 시킨다. start() 를 호출하지 않으면 동 순차 실행 된다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }

            one.start()
            two.start()

            println("The answer is ${one.await() + two.await()}")
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