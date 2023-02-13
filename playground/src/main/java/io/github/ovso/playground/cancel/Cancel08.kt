@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.cancel

import kotlinx.coroutines.*

object Cancel08 {
    /*
    Done 을 반환하기 전에 코루틴은 취소됩니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val result = withTimeoutOrNull(1300L) {
            Thread.currentThread().name.also {
                println(it)
            }
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // will get cancelled before it produces this result
        }
        println("Result is $result")    }
}