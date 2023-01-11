@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import io.github.ovso.playground.Context11.threadLocal
import kotlinx.coroutines.*

object Context11 {
    val threadLocal = ThreadLocal<String?>() // declare thread-local variable
}
    fun main(args: Array<String>) = runBlocking {
        threadLocal.set("main")
        println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
            println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
            yield()
            println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        job.join()
        println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }