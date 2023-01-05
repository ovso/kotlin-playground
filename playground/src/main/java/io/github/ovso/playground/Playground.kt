package io.github.ovso.playground

import kotlinx.coroutines.*
import kotlin.concurrent.thread

object Playground {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {

/*
        for (a in 1..100_000) {
            thread {
                Thread.sleep(1000L)
                print(".")
            }
        }
*/
        repeat(100_100) {
            launch {
                delay(1000L)
                print(".")
            }
        }
    }
}