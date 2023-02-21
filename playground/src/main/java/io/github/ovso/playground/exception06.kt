@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.exception06

import kotlinx.coroutines.*
import java.io.IOException

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) {
        val inner = launch { // all this stack of coroutines will get cancelled
            launch {
                launch {
                    throw IOException() // the original exception
                }
            }
        }
        try {
            inner.join()
        } catch (e: CancellationException) {
            println("Rethrowing CancellationException with original cause")
            throw e // cancellation exception is rethrown, yet the original IOException gets to the handler
        }
    }
    job.join()
}

/*
모든 처리가 끝난 후 parent 에러 핸들링이 처리되기 때문에 catch 블록이 먼저 실행된다.
parent 에러 핸들링(handler) 에서 잡히는 에러는 IOException 이다. catch 에서 throw e 로 예외를 발생시키더라도 throw e 는 억제된다.
 */

/*
Rethrowing CancellationException with original cause
CoroutineExceptionHandler got java.io.IOException
 */