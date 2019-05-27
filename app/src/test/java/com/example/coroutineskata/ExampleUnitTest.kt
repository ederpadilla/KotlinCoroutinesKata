package com.example.coroutineskata

import kotlinx.coroutines.*
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //exampleBlocking()
        //exampleBlickingispatcher()
        //`example Launch Global`()
        //`example Launch Waiting`()
        //`example Launch Corutine Scope`()
        //`example Launch Corutine Scope Custom Dispatcher`()
        //`example Async Await`()
        `example with Context`()
        assertEquals(4, 2 + 2)
    }

    suspend fun printDelayed(message: String) {
        delay(2000)
        println(message)
    }

    fun exampleBlocking() = runBlocking{
        println("one")
        printDelayed("two")
       /* runBlocking {
        }*/
        println("three")
    }

    fun exampleBlickingispatcher(){
        runBlocking(Dispatchers.Default) {
            println("one - from thread ${Thread.currentThread().name}")
            printDelayed("two - from thread ${Thread.currentThread().name}")
        }
        println("Three - from thread ${Thread.currentThread().name}")
    }

    fun `example Launch Global`() = runBlocking{
        println("one - from thread ${Thread.currentThread().name}")
        GlobalScope.launch {
            printDelayed("two - from thread ${Thread.currentThread().name}")
        }
        println("Three - from thread ${Thread.currentThread().name}")
        delay(3000)
    }

    fun `example Launch Waiting`() = runBlocking{
        println("one - from thread ${Thread.currentThread().name}")
        val job =  GlobalScope.launch {
            printDelayed("two - from thread ${Thread.currentThread().name}")
        }

        println("Three - from thread ${Thread.currentThread().name}")
        job.join()
    }

    fun `example Launch Corutine Scope`() = runBlocking{
        println("one - from thread ${Thread.currentThread().name}")
        launch(Dispatchers.IO) {
            printDelayed("two - from thread ${Thread.currentThread().name}")
        }
        println("Three - from thread ${Thread.currentThread().name}")
    }

    fun `example Launch Corutine Scope Custom Dispatcher`() = runBlocking{
        println("one - from thread ${Thread.currentThread().name}")
        val customDispatcher = Executors
            .newFixedThreadPool(2)
            .asCoroutineDispatcher()
        launch(customDispatcher) {
            printDelayed("two - from thread ${Thread.currentThread().name}")
        }
        println("Three - from thread ${Thread.currentThread().name}")
        (customDispatcher.executor as ExecutorService).shutdown()
    }

    suspend fun calculateHArdThings(startNum : Int) : Int {
        delay(1000)
        return startNum * 10
    }

    fun `example Async Await`() = runBlocking{
        val startTime = System.currentTimeMillis()
        val deferred1 = async{
            calculateHArdThings(10)
        }
        val deferred2 = async{
            calculateHArdThings(20)
        }
        val deferred3 = async{
            calculateHArdThings(30)
        }
        val sum = deferred1.await()+deferred2.await()+deferred3.await()
        println("async/await result = $sum")
        val endTime = System.currentTimeMillis()
        println("TimeTaken :  = ${endTime - startTime}")
    }

    fun `example Async Await more`() = runBlocking{
        val startTime = System.currentTimeMillis()
        val deferred1 = async{
            calculateHArdThings(10)
        }.await()
        val deferred2 = async{
            calculateHArdThings(20)
        }.await()
        val deferred3 = async{
            calculateHArdThings(30)
        }.await()
        val sum = deferred1+deferred2+deferred3
        println("async/await result = $sum")
        val endTime = System.currentTimeMillis()
        println("TimeTaken :  = ${endTime - startTime}")
    }

    fun `example with Context`() = runBlocking{
        val startTime = System.currentTimeMillis()
        val result1 = withContext(Dispatchers.Default){
            calculateHArdThings(10)
        }
        val result2 = withContext(Dispatchers.Default){
            calculateHArdThings(20)
        }
        val result3 = withContext(Dispatchers.Default){
            calculateHArdThings(30)
        }
        val sum = result1+result2+result3
        println("async/await result = $sum")
        val endTime = System.currentTimeMillis()
        println("TimeTaken :  = ${endTime - startTime}")
    }
}
