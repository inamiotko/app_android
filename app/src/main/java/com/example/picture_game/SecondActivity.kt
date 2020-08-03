package com.example.picture_game

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.VelocityTracker
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ImageSwitcher
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.solver.widgets.Rectangle
import kotlin.random.Random


class SecondActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var counter = 0
        setContentView(R.layout.activity_second)
        val imgCat: ImageView = findViewById(R.id.cat_img)
        val imgDog: ImageView = findViewById(R.id.dog_img)

        val images = intArrayOf(R.drawable.bone,
            R.drawable.meat, R.drawable.fish, R.drawable.mouse)
        val imgSwitcher = findViewById<ImageSwitcher>(R.id.imsw)
        imgSwitcher?.setFactory {
            val imgView = ImageView(applicationContext)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setPadding(8, 8, 8, 8)
            imgView
        }
        val timer = object : CountDownTimer(3000000, 2999) {
            override fun onFinish() {
            }
            override fun onTick(millisUntilFinished: Long) {
                val rand = (0..3).random()
                imgSwitcher.setImageResource(images[rand])

                when((1..5).random())
                {
                    1->imgSwitcher.setInAnimation(
                        applicationContext,
                        R.anim.slide1)
                    2->imgSwitcher.setInAnimation(
                        applicationContext,
                        R.anim.slide2)
                    3->imgSwitcher.setInAnimation(
                        applicationContext,
                        R.anim.slide3)
                    4->imgSwitcher.setInAnimation(
                        applicationContext,
                        R.anim.slide4)
                    else->imgSwitcher.setInAnimation(
                        applicationContext,
                        R.anim.slide5)
                }
            }
        }
        timer.start()

        var listener = View.OnTouchListener(function = {view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }
            true
        })
        imgCat.setOnTouchListener(listener)
        imgDog.setOnTouchListener(listener)
        }

    fun points(counter: Int): Int
    {
        TODO()
      return counter
    }



}





