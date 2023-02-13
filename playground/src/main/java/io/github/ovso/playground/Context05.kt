@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Context05 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        println("My job is ${coroutineContext[Job]?.isActive}")
    }
}

/*
coroutineContext[Job]?.isActive 의 확장함수는 isActive 이다.
 */