@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

class Activity {
    private val mainScope = CoroutineScope(Dispatchers.Default) // use Default for test purposes

    fun destroy() {
        mainScope.cancel()
    }

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            mainScope.launch {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                println("Coroutine $i is done")
            }
        }
    }
} // class Activity ends

object Context10 {
    /*
    doSomething() 에서 10개의 코루틴이 동시에 실행됩니다. 각 코루틴은 일정하게 증가하는 지연시간을 갖고 있습니다.
    500L 이후에 코루틴을 취소하면 500L 을 넘기는 코루틴은 모두 취소 됩니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val activity = Activity()
        activity.doSomething() // run test function
        println("Launched coroutines")
        delay(500L) // delay for half a second
        println("Destroying activity!")
        activity.destroy() // cancels all coroutines
        delay(1000) // visually confirm that they don't work
    }
}