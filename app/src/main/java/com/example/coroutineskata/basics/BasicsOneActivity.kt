package com.example.coroutineskata.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutineskata.R
import com.example.coroutineskata.util.DebugUtils
import com.example.coroutineskata.util.DebugUtils.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BasicsOneActivity : AppCompatActivity() {

    val TAG = BasicsOneActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basics_one)
        //firstCoroutine()
        //runBlockingSample()
        //waitForAJob()
        sampleBlocking()
    }

    private fun firstCoroutine() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            DebugUtils.log(TAG,"World!") // print after delay
        }
        DebugUtils.log(TAG,"Hello Corrutines")
        Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
    }

    private fun runBlockingSample() {
        GlobalScope.launch { // launch a new coroutine in background and continue
            delay(1000L)
            log(TAG,"World!")
        }
        log(TAG,"Hello,") // main thread continues here immediately
        log(TAG,"Test 1")
        log(TAG,"Test 2")
        log(TAG,"Test 3")
        runBlocking {     // but this expression blocks the main thread
            log(TAG,"Test Run Blocking")
            delay(2000L)  // ... while we delay for 2 seconds to keep JVM alive
        }
    }

    fun waitForAJob() = runBlocking {
        //sampleStart
        val job = GlobalScope.launch { // launch a new coroutine and keep a reference to its Job
            //delay(2000L)
            log(TAG,"World!")
        }
        log(TAG,"Hello,")
        job.join() // wait until child coroutine completes
                   //sampleEnd
    }

    private suspend fun printDelayed(message : String){
        delay(1000)
        log(TAG,message)
    }

     private fun sampleBlocking(){
        log(TAG,"one")
         runBlocking {
             printDelayed("Two")
         }
        log(TAG,"three")
        log(TAG,"four")

    }

}
