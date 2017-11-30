package edu.harding.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
	private var mTimer: Timer? = null
	private var mCount = 0
	private var mTimerRunning = false
	private val TIMER_DELAY = 1000L
			
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		btnTimerControl.setOnClickListener {
			if (mTimerRunning)
				stopTimer()
			else
				startTimer()
		}
	}
	
	private fun stopTimer() {
		mTimer?.cancel()
		mTimer = null
		mTimerRunning = false
		mCount = 0
		txtTimerStatus.text = getText(R.string.timer_stopped)
		btnTimerControl.text = getText(R.string.timer_start)
	}
	
	private fun startTimer() {
		// Create a timer that executes every second.
		mTimer = Timer()
		mTimer?.schedule(object : TimerTask() {
			override fun run() = runOnUiThread {
				// Update the view.
				txtTimerStatus.text = "Count: $mCount"
				mCount++
			}
		}, 0, TIMER_DELAY)
		
		
		mTimerRunning = true
		btnTimerControl.text = getText(R.string.timer_stop)
	}
}
