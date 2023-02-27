@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.supervision03

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    supervisorScope {
        val child = launch(handler) {
            println("The child throws an exception")
            throw AssertionError()
        }
        println("The scope is completing")
    }
    println("The scope is completed")}

/*
예상 출력
The child throws an exception
CoroutineExceptionHandler got $exception
The scope is completing
The scope is completed
 */
/*
실제 출력
The scope is completing
The child throws an exception
CoroutineExceptionHandler got java.lang.AssertionError
The scope is completed
 */