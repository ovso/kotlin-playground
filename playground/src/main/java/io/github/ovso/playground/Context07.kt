@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Context07 {
    /*
    request 코루틴 안에 launch 코루틴(child) 세 개가 동시에 시작됩니다.
    request.join() 은 세 개의 코루틴(요청)이 완료될 때까지 기다리게 합니다.
    세 개의 자식 코루틴이 부모의 코루틴을 상속하고 있기 때문에 이렇게 동작합니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        // launch a coroutine to process some kind of incoming request
        val request = launch {
            repeat(3) { i -> // launch a few children jobs
                launch  {
                    delay((i + 1) * 200L) // variable delay 200ms, 400ms, 600ms
                    println("Coroutine $i is done")
                }
            }
            println("request: I'm done and I don't explicitly join my children that are still active")
        }
        request.join() // wait for completion of the request, including all its children
        println("Now processing of the request is complete")
    }
}