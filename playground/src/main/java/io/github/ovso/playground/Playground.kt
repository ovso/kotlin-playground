package io.github.ovso.playground

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

object Playground {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello World!")
        runBlocking {
            aa()
        }
    }

    private suspend fun aa() {
        coroutineScope {
            println("zzzzz")
        }
    }
}