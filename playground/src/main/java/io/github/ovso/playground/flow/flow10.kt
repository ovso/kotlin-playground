@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch")

// 패키지를 다르게 하여 다른 클래스와의 컴파일 에러를 막습니다.
package io.github.ovso.playground.flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}

object Flow10 {
    /*
        take 중간 연산자는 해당 제한에 도달하면 플로우 실행을 취소합니다.
        코루틴의 취소는 항상 예외를 발생시키므로 finally 블록이 실행됩니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        numbers()
            .take(2)
            .collect { value -> println(value) }
    }
}