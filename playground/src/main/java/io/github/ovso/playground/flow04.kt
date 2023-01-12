@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow04

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

object Flow04 {

    /*
    flow 빌더를 통해 메인스레들 차단하지 않고 값을 방출(emit) 합니다.
    코루틴 범위 내에서 flow 는 suspend 키워드를 사용하지 않아도 일시정지 기능을 사용할 수 있습니다.
    flow 빌더 내부를 보면, collectSafely 함수에서 suspend 함수를 확인할 수 있습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // Launch a concurrent coroutine to check if the main thread is blocked
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(100)
            }
        }
        // Collect the flow
        simple().collect { value -> println(value) }
    }
}