@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow26

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) // emit next value
    }
}

/** check 로 예외 발생 후 값이 방출되지 않습니다.*/
fun main() = runBlocking {
    try {
        simple().collect { value ->
            println(value)
            check(value <= 3) { "Collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}