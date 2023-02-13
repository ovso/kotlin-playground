@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.context

import kotlinx.coroutines.*

object Context08 {
    /*
    코루틴 네임을 지정합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking(CoroutineName("main")) {
        log("Started main coroutine")
        // run two background value computations
        val v1 = async(CoroutineName("v1coroutine")) {
            delay(500)
            log("Computing v1")
            252
        }
        val v2 = async(CoroutineName("v2coroutine")) {
            delay(1000)
            log("Computing v2")
            6
        }
        log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
    }
}