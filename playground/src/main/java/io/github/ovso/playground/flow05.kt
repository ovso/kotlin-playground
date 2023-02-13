@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow05

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun simple(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

object Flow05 {

    /*
    코루틴은 흐름이 수집될 때까지 실행되지 않습니다. Rx 의 구독과 비슷합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        println("Calling simple function...")
        val flow = simple()
        println("Calling collect...")
        flow.collect {value -> println(value) }
        println("Calling collect again...")
        flow.collect { value -> println(value) }
    }
}