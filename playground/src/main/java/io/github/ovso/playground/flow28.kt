@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow28

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<String> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) // emit next value
    }
}.map { value ->
    check(value <= 1) { "Crashed on $value" }
    "string $value"
}

/** check 로 예외 발생 후 값이 방출되지 않습니다.*/
fun main() = runBlocking {
    simple()
        .catch { e -> emit("Caught $e") } // 예외 발생시에 방출합니다.
        .collect { value ->
        println(value)
    }

}

// https://kotlinlang.org/docs/flow.html#exception-transparency