@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.cancel

import kotlinx.coroutines.*

var acquired = 0

class Resource {
    init {
        acquired++
    }

    fun close() {
        acquired--
    }
}

object Cancel09 {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            repeat(100_000) {
                launch {
                    val resource = withTimeout(60) {
                        delay(50)
                        Resource()
                    }
                    resource.close()
                }
            }
        }
        println(acquired)
    }
}