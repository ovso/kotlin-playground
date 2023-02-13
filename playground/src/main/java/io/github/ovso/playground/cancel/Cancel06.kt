@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.cancel

import kotlinx.coroutines.*

object Cancel06 {
    /*
      일반적으로 finally{..} 에서는 코루틴을 재개 할 수 없습니다.
      코루틴을 취소한 상태이기 때문입니다. 그러나 withcontext(NonCancellable){..} 을 사용하면 코루틴이 가능합니다.
      물론, launch(NonCancellable){..} 사용해도 코루틴을 재개 할 수 없습니다. launch 는 이미 종료된 코루틴의 범위이기 때문입니다.
      finally{..} 의 코루틴은 유의해서 사용해야 합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val job = launch(Dispatchers.Default) {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    delay(2000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }

            /*
                launch(NonCancellable) {
                    delay(2000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
*/
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
    }
}