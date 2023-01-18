@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow19

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // 비동기를 기다립니다.
        emit(i) // emit next value
    }
}

/**
 * 데이터를 소비할 때 시간이 방출한 값 하나의 시간보다 오래 걸리면 최근 값 처리합니다.
 * 그렇지 않으면 모두 처리합니다.
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple()
            .collectLatest { value -> // 취소하고 최신 값으로 다시 시작합니다.
                println("Collecting $value")
                delay(300) // 300ms 동안 처리한다고 가정합니다.
                println(value)
                println("Done $value")
            }
    }
    println("Collected in $time ms")
}

// https://kotlinlang.org/docs/flow.html#processing-the-latest-value