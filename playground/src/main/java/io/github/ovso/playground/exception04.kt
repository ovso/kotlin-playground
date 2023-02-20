@file:Suppress("SpellCheckingInspection", "PackageDirectoryMismatch", "OPT_IN_USAGE")

// 패키지를 다르게 하여 다른 클래스와의 충돌(컴파일 에러)을 막습니다.
package io.github.ovso.playground.exception04

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) {
        launch { // the first child
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    println("Children are cancelled, but exception is not handled until all children terminate")
                    delay(100)
                    println("The first child finished its non cancellable block")
                }
            }
        }
        launch { // the second child
            delay(10)
            println("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
}
// 두 번째 launch 가 취소 되면 첫 번째 launch 도 취소가 됩니다. 동일한 child 이기 때문입니다.
// 모든 child 가 종료 되어야만 child 의 예외가 parent에서 처리됩니다.
/*
예상
Second child throws an exception
CoroutineExceptionHandler got ArithmeticException()
Children are cancelled, but exception is not handled until all children terminate
The first child finished its non cancellable block
 */

/*
결과
Second child throws an exception
Children are cancelled, but exception is not handled until all children terminate
The first child finished its non cancellable block
CoroutineExceptionHandler got ArithmeticException()
 */