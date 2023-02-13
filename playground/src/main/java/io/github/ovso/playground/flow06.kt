@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow06

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

object Flow06 {

    /*
    Flow 는 코루틴의 일박적인 취소를 지원합합니다.
    평소와 같이 일시중지(suspend) 에서 취소를 할 수 있습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        withTimeoutOrNull(250) { // Timeout after 250ms
            simple().collect { value -> println(value) }
        }
        println("Done")
    }
}