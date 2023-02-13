@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow

import kotlinx.coroutines.*

fun simple(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(100)
        yield(i)
    }
}

object Flow02 {

    /*
    시퀀스 빌더를 사용하여 값을 출력합니다. 그러나 기본 스레드를 차단하고 있습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        simple().forEach { value -> println(value) }
    }
}