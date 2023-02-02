@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow30

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}
/** collect 에서 처리하던 것을 onEach 로 이동하여 처리했습니다. 예외는 catch 중간 연산자보다 앞서 발생하기 때문에 예외를 잡습니다.*/
/** try/catch 하지 않고도 모든 예외를 catch 할 수 있습니다.*/
fun main() = runBlocking {
    simple()
        .onEach { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
        .catch { e -> println("Caught $e") }
        .collect()
}
/*
Emitting 1
1
Emitting 2
Caught java.lang.IllegalStateException: Collected 2

 */
// https://kotlinlang.org/docs/flow.html#catching-declaratively