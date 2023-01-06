@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Cancel03 {
    /**
     * 취소를 했으나 취소가 안되는 상황 입니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val job = launch(Dispatchers.Default) {
            repeat(5) { i ->
                try {
                    println("job: I'm sleeping $i ...")
                    delay(500)
                } catch (e: Exception) {
                    println(e)
                }
            }
        }
        delay(1300L)
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 취소하고 완료를 기다리고 있으나 취소되지 않습니다.
        println("main: Now I can quit.")
    }
}