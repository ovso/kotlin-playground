@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow29

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

/** catch 중간 연산자를 업스트림 예외만 catch 합니다. catch 중간 연산자 아래의 스트림은 catch 하지 않아 예외가 발생합니다.
 * collect 안에서 예외 발생하면 println 하지 않습니다.
 * */
fun main() = runBlocking {
    simple()
        .catch { e -> println("Caught $e") } // does not catch downstream exceptions
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}

// https://kotlinlang.org/docs/flow.html#transparent-catch