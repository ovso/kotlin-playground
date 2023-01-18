@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow18

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
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
 * 1이 처리(방출+소비)되기 전에 2, 3 이 들어왔기 때문에 2가 1에 병합되고 3이 소비됩니다.
 *
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple()
            .conflate() // 소비 시간에 따라 방출을 통합합니다. 통합된 데이터를 소비되지 않습니다.
            .collect { value ->
                delay(300) // 300ms 동안 처리한다고 가정합니다.
                println(value)
            }
    }
    println("Collected in $time ms") // 대략 1200 ms(숫자 3개, 각 400ms)
}

// https://kotlinlang.org/docs/flow.html#conflation