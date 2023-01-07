@file:Suppress("SpellCheckingInspection")

package io.github.ovso.playground

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

object Cancel12 {
    /*
    doSomethingUsefulOne() 와 doSomethingUsefulTwo() 사이에 종속성을 없애는 동시성을 사용하여 처리속도가 2배 빠릅니다.
     */
    @JvmStatic
    fun main(args: Array<String>) = runBlocking {
        Thread.currentThread().name.also {
            println(it)
        }
        val time = measureTimeMillis {
            Thread.currentThread().name.also {
                println(it)
            }
            val one = async {
                Thread.currentThread().name.also {
                    println(it)
                }

                doSomethingUsefulOne()
            }
            val two = async {
                Thread.currentThread().name.also {
                    println(it)
                }

                doSomethingUsefulTwo()
            }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        Thread.currentThread().name.also {
            println(it)
        }

        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        Thread.currentThread().name.also {
            println(it)
        }

        return 29
    }
}