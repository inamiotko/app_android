package com.example.picture_game

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Layout
import android.util.Log
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs


class SecondActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var catB = false
        var dogB = false
        setContentView(R.layout.activity_second)
        val imgCat: ImageView = findViewById(R.id.cat_img)
        val imgDog: ImageView = findViewById(R.id.dog_img)
        var rand =0
        val exitButton: Button = findViewById(R.id.back_button2)
        val results: TextView = findViewById(R.id.couter_text2)
        val images = intArrayOf(R.drawable.bone,
            R.drawable.meat, R.drawable.fish, R.drawable.mouse)

        val imgSwitcher = findViewById<ImageSwitcher>(R.id.imsw)
        val imgsR = imgSwitcher.clipBounds
        val dogR = imgDog.clipBounds
        val catR = imgDog.clipBounds

        imgSwitcher?.setFactory {
            val imgView=ImageView(applicationContext)
            imgView.scaleType = ImageView.ScaleType.FIT_CENTER
            imgView.setPadding(8, 8, 8, 8)
            imgView
        }
        var tick = 3
        var counter = 0
        val timer = object : CountDownTimer(3000000, 1000) {
            override fun onFinish() {
            }
            override fun onTick(millisUntilFinished: Long) {
                tick++

                if(tick%3==0){
                rand = (0..3).random()
                imgSwitcher.setImageResource(images[rand])
                if(catB || dogB)counter++
                when ((1..5).random()) {
                    1 -> ObjectAnimator.ofFloat(imgSwitcher, "translationX", -150f, 100f, 200f, -150f).apply {
                        duration = 3000
                        start()
                        }
                    2 -> ObjectAnimator.ofFloat(imgSwitcher, "rotation", 0f, 300f, -180f, 180f).apply{
                        duration = 3000                 // Duration in milliseconds
                        interpolator// E.g. Linear, Accelerate, Decelerate
                        start()
                        }
                    3-> ObjectAnimator.ofFloat(imgSwitcher, "translationY", 100f, -150f,600f,-200f).apply{
                    duration = 3000                 // Duration in milliseconds
                    interpolator// E.g. Linear, Accelerate, Decelerate
                    start()
                        }
                    4->ObjectAnimator.ofFloat(imgSwitcher, "translationY", 150f, 200f, 100f, -50f).apply{
                        duration = 3000                 // Duration in milliseconds
                        interpolator// E.g. Linear, Accelerate, Decelerate
                        start()
                        }
                    else-> ObjectAnimator.ofFloat(imgSwitcher, "translationX", 0f, 100f, -180f, 200f).apply{
                        duration = 3000                 // Duration in milliseconds
                        interpolator// E.g. Linear, Accelerate, Decelerate
                        start()
                        }
                    }
                }

                Log.i("TAG", "dog " + imgDog.y + "switcher X  " + imgSwitcher.x + "switcher Y  " + imgSwitcher.y + "dog H " + imgDog.height )
            }
        }
        timer.start()

        exitButton.setOnClickListener {
            timer.cancel()
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)}
        var listener = View.OnTouchListener(function = {view, motionEvent ->

            if (motionEvent.action == ACTION_MOVE) {
                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
                when(rand)
                {
                    0 ->
                        if((imgDog.x + imgDog.width/2) >= (imgSwitcher.x + imgSwitcher.width/2)
                            && (imgDog.y + imgDog.height/2) >= (imgSwitcher.y + imgSwitcher.height/2)) dogB=true
                    1 ->
                        if((imgDog.x + imgDog.width/2) >= (imgSwitcher.x + imgSwitcher.width/2)
                            && (imgDog.y + imgDog.height/2) >= (imgSwitcher.y + imgSwitcher.height/2)) dogB=true

                    2 ->
                        if((imgCat.x + imgCat.width/2) >= (imgSwitcher.x + imgSwitcher.width/2)
                            && (imgCat.y + imgCat.height/2) >= (imgSwitcher.y + imgSwitcher.height/2)) catB=true
                    3 ->
                        if((imgCat.x + imgCat.width/2) >= (imgSwitcher.x + imgSwitcher.width/2)
                            && (imgCat.y + imgCat.height/2) >= (imgSwitcher.y + imgSwitcher.height/2)) catB=true
                    else -> {dogB = false
                        catB = false}

                }
                results.text = counter.toString()
                //Log.i("TAG", "dog " + imgDog.x +  "switcher "+ location[1] + "view "+ view.x)
            }
            true
        })
        imgCat.setOnTouchListener(listener)
        imgDog.setOnTouchListener(listener)


        }






}





