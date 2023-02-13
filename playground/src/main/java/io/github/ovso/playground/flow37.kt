@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow37

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun foo(): Flow<Int> = flow {
    for (i in 1..5) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    foo().collect { value ->
        if (value == 3) cancel()
        println(value)
    }
}

/*
Emitting 1
1
Emitting 2
2
Emitting 3
IllegalStateException: Scope cannot be cancelled because it does not have a job: $this
 */