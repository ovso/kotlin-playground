@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.flow15

import io.github.ovso.playground.flow14.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100) // CPU를 많이 사용하는 방식으로 계산한다고 가정합니다.
        log("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default) // Flow Builder에서 CPU를 많이 사용하는 코드의 컨텍스트를 변경하는 올바른 방법 입니다.

/*
Flow 는 백그라운드 쓰레드에서 실행되는 반면, collect 는 메인 쓰레드에서 실행됩니다.
 */
fun main() = runBlocking<Unit> {
    simple().collect { value ->
        log("Collected $value")
    }
}