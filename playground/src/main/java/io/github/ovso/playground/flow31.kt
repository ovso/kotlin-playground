@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow31

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking {
    try {
        simple().collect { value -> println(value) }
    } finally {
        println("Done")
    }
}

/*
1
2
3
Done
 */