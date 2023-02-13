@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground.context

import kotlinx.coroutines.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

object Context03 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        val a = async {
            log("I'm computing a piece of the answer")
            6
        }
        val b = async {
            log("I'm computing another piece of the answer")
            7
        }
        log("The answer is ${a.await() * b.await()}")
    }
}

/*
세 개의 코루틴 모두 runBlocking 에서 실행되면 기본 쓰레드에서 동작합니다.
*/

/*
코루틴을 디버깅 합니다.
세 개의 코루틴이 존재합니다.
1. Line 20 의 log 가 첫번째 코루틴 입니다. log는 RUNNING 상태입니다. 비동기 a, b 는 CREATED 상태입니다.
2. 비동기 a 가 RUNNING 상태입니다. log는 SUSPENDED 상태입니다. b 는 CREATED 상태입니다.
3. a 는 연산을 마치고 사라집니다. 비동기 b 가 RUNNING 상태입니다. log는 SUSPENDED 상태입니다.
 */