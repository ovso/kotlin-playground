@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow34

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = (1..3).asFlow()


/**
 * onCompletion 중간연산자와 catch 연산자와 차이점으로,
 * onCompletion 은 모든 예외를 확인하고 업스트림 Flow 가 성공적으로 완료된 경우에만 null 예외를 수신한다는 것입니다.
 *
 * 아래의 코드에서는 예외로 스트림이 중단되었기 때문에 onCompletion 이 null 이 아님을 확인할 수 있습니다.
 */
fun main() = runBlocking {
    simple()
        .onCompletion { cause -> println("Flow completed with $cause") }
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}
/*
1
Flow completed with java.lang.IllegalStateException: Collected 2
 */