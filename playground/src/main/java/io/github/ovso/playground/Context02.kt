@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Context02 {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        // 일반 코드에서느 사용하지 않는 디스패처 입니다.
        // 일시중단 함수가 실행되지 전까지만 상속한 쓰레드(main) 에서 실행됩니다.
        // 일시중단 함수가 실행된 이후에는 기본 쓰레드로 실행됩니다.
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        // Dispatcher 를 설정하지 않으면 상속된 쓰레드에서 실행됩니다.
        // 이 경우 runBlocking 의 main 쓰레드 입니다.
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }
}