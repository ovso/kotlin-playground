@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow17

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
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
 * Flow에서 순차적으로 실행하는 것과는 반대로, Buffer 연산자를 사용하여 코드 수집과 동시에 simple() 방출 코드를 실행할 수 있습니다.
 * buffer 를 사용하지 않으면, 발행에서의 100ms + 소비에서의 300ms 가 순차적으로 발생하면서 모든 데이터를 소비하는 시간이 1200ms 를 넘길 것이라 예상합니다. 이는 하나의 코루틴에서 데이터 방출과 소비를 실행합니다.
 * 그러나 buffer 를 사용하면 방출과 소비가 다른 코루틴 실행됩니다. 때문에 모든 데이터를 소비하는 시간이 1000ms 안팎이라 예상할 수 있습니다.
 *
 * buffer 를 사용하면 simple() 을 처리(방출)하는데 300 ms 시간이 소요됩니다. 소비와 무관합니다. 소비에서의 지연만 있습니다.
 * buffer 를 사용하지 않으면 simple() 에서 데이터 하나를 방출하는데 400ms 가 걸립니다.
 */
fun main() = runBlocking {
    val time = measureTimeMillis {
        simple()
            .buffer() // 버퍼를 방출, 기다리지 않음
            .collect { value ->
                delay(300) // 300ms 동안 처리한다고 가정합니다.
                println(value)
            }
    }
    println("Collected in $time ms") // 대략 1200 ms(숫자 3개, 각 400ms)
}

// https://kotlinlang.org/docs/flow.html#buffering
// https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/buffer.html
// https://kotlinworld.com/253
