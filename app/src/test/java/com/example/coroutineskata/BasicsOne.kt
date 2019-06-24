package com.example.coroutineskata

import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test
import java.lang.System.currentTimeMillis

class BasicsOne {

    @Test

    fun main (){
        `with timeout or null`()
        print("Hello after coroutine")
        Assert.assertEquals(4, 2 + 2)
    }

    private fun `wait for a job`() = runBlocking {
        val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join() // wait until child coroutine completes
    }

    fun `lauch and run blocking`() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine in the scope of runBlocking
            delay(1000L)
            println("World!")
        }
        println("Hello,")
    }


    fun `different taks`() = runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking launch 1")
        }

        coroutineScope { // Creates a coroutine scope
            launch {
                delay(500L)
                println("Task from nested launch coroutine scope 2")
            }

            delay(100L)
            println("Task from coroutine scope 3") // This line will be printed before the nested launch
        }

        println("Coroutine scope is over 4") // This line is not printed until the nested launch completes
    }

    fun `extract function`() = runBlocking {
        launch { doWorld() }
        println("Hello,")
    }

    // this is your first suspending function
    private suspend fun doWorld() {
        delay(1000L)
        println("World!")
    }

    private fun `repeat task`() = runBlocking {
        repeat(100_000) { // launch a lot of coroutines
            launch {
                delay(1000L)
                print("hi.")
            }
        }
    }

    private fun `Im sleeping twice a second`() = runBlocking {
        GlobalScope.launch {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // just quit after delay
    }

    private fun `cancel job`() = runBlocking {
        //sampleStart
        val job = launch {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancel() // cancels the job
        job.join() // waits for job's completion
        println("main: Now I can quit.")
        //sampleEnd
    }

    fun `cancel and join`() = runBlocking {
        //sampleStart
        val startTime = currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) { // computation loop, just wastes CPU
                // print a message twice a second
                if (currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
//sampleEnd
    }

    private fun `try and cancel`() = runBlocking {
        //sampleStart
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("job: I'm running finally")
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
//sampleEnd
    }

    fun `no cancelable`() = runBlocking {
        //sampleStart
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // cancels the job and waits for its completion
        println("main: Now I can quit.")
//sampleEnd
    }

    fun `with time out`() = runBlocking {
        //sampleStart
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
//sampleEnd
    }


    private fun `with timeout or null`() = runBlocking {
        //sampleStart
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // will get cancelled before it produces this result
        }
        println("Result is $result")
//sampleEnd
    }

}