@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Cancel10 {
    @JvmStatic
    fun main(args: Array<String>) {
        runBlocking {
            repeat(100_000) { // Launch 100K coroutines
                launch {
                    var resource: Resource? = null // Not acquired yet
                    try {
                        withTimeout(90) { // Timeout of 60 ms
                            delay(50) // Delay for 50 ms
                            resource =
                                Resource() // Store a resource to the variable if acquired
                        }
                        // We can do something else with the resource here
                    } finally {
                        resource?.close() // Release the resource if it was acquired
                    }
                }
            }
        }
        // Outside of runBlocking all coroutines have completed
        println(acquired) // Print the number of resources still acquired
    }
}