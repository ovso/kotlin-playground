@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.exception05

import kotlinx.coroutines.*
import java.io.IOException

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
    }
    val job = GlobalScope.launch(handler) {
        launch {
            try {
                delay(Long.MAX_VALUE) // it gets cancelled when another sibling fails with IOException
            } finally {
                throw ArithmeticException() // the second exception
            }
        }
        launch {
            delay(100)
            throw IOException() // the first exception
        }
        delay(Long.MAX_VALUE)
    }
    job.join()
}
/*
두 번째 child 가 먼저 예외를 발생합니다.
첫 번째 child 가 취소 됩니다.
첫 번재 finally 블록에서 발생한 예외는 두 번째 child 에서 발생한 예외에 억제됩니다.
이후 에러 핸들러에서 첫번째 예외에 첨부 됩니다.
 */


/*
출력
CoroutineExceptionHandler got java.io.IOException with suppressed [java.lang.ArithmeticException]
 */