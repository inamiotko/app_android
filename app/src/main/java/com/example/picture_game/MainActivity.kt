package com.example.picture_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img: ImageView = findViewById(R.id.image_pet)
        var seconds = 0
        val catButton: Button = findViewById(R.id.cat_button)
        val dogButton: Button = findViewById(R.id.dog_button)
        val toast = Toast.makeText(this, "game over", Toast.LENGTH_SHORT)
        val toastBad = Toast.makeText(this, "WRONG!", Toast.LENGTH_SHORT)
        var counter = 0
        val resultTextView: TextView = findViewById(R.id.counts3_text)
        val onetext: TextView = findViewById(R.id.countdown_text)
        val twotext: TextView = findViewById(R.id.countdown_text2)
        val threetext: TextView = findViewById(R.id.countdown_text3)
        val timer = object : CountDownTimer(300000, 1500) {
            override fun onTick(millisUntilFinished: Long) {
                seconds++
               if (seconds==1) {
                   onetext.visibility = View.INVISIBLE
                   twotext.visibility = View.INVISIBLE
                   threetext.visibility = View.VISIBLE
                   img.visibility = View.INVISIBLE
               }
                else if (seconds ==2 ) {
                   onetext.visibility = View.INVISIBLE
                   twotext.visibility = View.VISIBLE
                   threetext.visibility = View.INVISIBLE
                   img.visibility = View.INVISIBLE
               }
                else if (seconds ==3) {
                   onetext.visibility = View.VISIBLE
                   twotext.visibility = View.INVISIBLE
                   threetext.visibility = View.INVISIBLE
                   img.visibility = View.INVISIBLE
               }
                else {
                   onetext.visibility = View.INVISIBLE
                   img.visibility = View.VISIBLE
                   pickNumber()
                }
                val nr = pickNumber()
                if (nr in 1..6) {
                    dogButton.setOnClickListener { counter++ }
                    catButton.setOnClickListener { toastBad.show() }
                } else {
                    catButton.setOnClickListener { counter++ }
                    dogButton.setOnClickListener { toastBad.show() }
                }
                resultTextView.text = counter.toString()
            }

            override fun onFinish() {
                toast.show()
            }
        }

        timer.start()

    }
    fun pickNumber(): Int {
        val number = Rand(12)
        val numberGenerated = number.generateNumber()
        val image: ImageView = findViewById(R.id.image_pet)
        val drawableResource = when (numberGenerated) {
            1 -> R.drawable.dog1
            2 -> R.drawable.dog2
            3 -> R.drawable.dog3
            4 -> R.drawable.dog4
            5 -> R.drawable.dog5
            6 -> R.drawable.dog6
            7 -> R.drawable.cat1
            8 -> R.drawable.cat2
            9 -> R.drawable.cat3
            10 -> R.drawable.cat4
            11 -> R.drawable.cat5
            else -> R.drawable.cat6
        }
        image.setImageResource(drawableResource)
        return numberGenerated
    }
        class Rand(private val number: Int) {
            fun generateNumber(): Int {
                return ((1..number).random())
            }

        }

}