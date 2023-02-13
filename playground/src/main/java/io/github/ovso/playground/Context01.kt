@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*

object Context01 {
    /*
    출력 순서를 보장되지 않습니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {
        /*
        launch{...} 가 매개변수 없이 사용되면 실행중인 CoroutineScope 에서 컨텍스트(디스패처를 말함)를 상속합니다.
        이 경우 메인스레드에서 실행되는 runBlocking 코루틴의 컨텍스트를 상속합니다.
         */
        launch { // context of the parent, main runBlocking coroutine
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        // Unconfined 는 메인 스레드에서도 실행되는 것처럼 보이지만 실제로는 나중에 설명하는 다른 메커니즘입니다.
        launch(Dispatchers.Unconfined) { // not confined -- will work with main thread
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        // 공유 백그라운드 스레드 풀을 사용합니다.
        launch(Dispatchers.Default) { // will get dispatched to DefaultDispatcher
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        /*
        newSingleThreadContext는 코루틴이 실행할 스레드를 생성합니다.
        전용 스레드는 매우 비싼 리소스입니다.
        실제 애플리케이션에서는 더 이상 필요하지 않을 때 close 함수를 사용하여 해제하거나 최상위 변수에 저장하고
        애플리케이션 전체에서 재사용해야 합니다.
         */
        launch(newSingleThreadContext("MyOwnThread")) { // will get its own new thread
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }
}