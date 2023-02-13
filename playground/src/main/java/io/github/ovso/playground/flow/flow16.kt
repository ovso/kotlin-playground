@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow16

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // 비동기를 기다립니다.
        emit(i) // emit next value
    }
}
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().collect { value ->
            delay(300) // 300ms 동안 처리한다고 가정합니다.
            println(value)
        }
    }
    println("Collected in $time ms") // 대략 1200 ms(숫자 3개, 각 400ms)
}

// https://kotlinlang.org/docs/flow.html#buffering