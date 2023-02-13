@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Context04 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {
                    log("Started in ctx1")
                    withContext(ctx2) {
                        log("Working in ctx2")
                    }
                    log("Back to ctx1")
                }
            }
        }
    }
}