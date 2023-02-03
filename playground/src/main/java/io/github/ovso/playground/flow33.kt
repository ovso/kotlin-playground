@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow33

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

/**
 * onCompletion 연산자는 catch 와 달리 예외를 처리하지 않습니다
 * 예외는 여전히 다운스트림으로 흐릅니다.
 * onCompletion 중간연산자에게 전달되며 catch 연산자로 처리할 수 있습니다.
 */
fun main() = runBlocking {
    simple()
        .onCompletion { cause ->
            if (cause != null) println("Flow completed exceptionally: $cause")
        }
        .catch { cause ->
            println("Caught exception $cause")
        }
        .collect { value ->
            println(value)
        }
}
// 예상
/*
1
Caught exception java.lang.RuntimeException
Flow completed exceptionally
 */

// 실제
/*
1
Flow completed exceptionally
Caught exception java.lang.RuntimeException
 */