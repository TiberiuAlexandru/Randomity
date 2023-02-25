package com.codalitylabs.randomity

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.AnimationSet
import android.widget.TextView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraintLayout: ConstraintLayout = findViewById(R.id.mainLayout)

        val animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable

        animationDrawable.setEnterFadeDuration(1000)

        animationDrawable.setExitFadeDuration(3000)

        animationDrawable.start()

        val titleEmoji = "\uD83C\uDFB2"
        val titleWelcomeText = findViewById<TextView>(R.id.titleText)
        titleWelcomeText.text = "Randomity $titleEmoji by CodalityLabs"

        val rotationXforIdea = 360f

        val genres = arrayOf("Sci-Fi", "Modern", "Fantasy", "WW2", "Ancient", "Pre-Historic")
        var generatedIdea = findViewById<TextView>(R.id.generatedIdea)
        val generateButton: Button = findViewById(R.id.generateButton)
        val spinner: Spinner = findViewById(R.id.genreSpinner)
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)

        val scaleDown = ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleDown.duration = 300
        val scaleUp = ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleUp.duration = 300

        val animation = AnimationSet(true)
        animation.addAnimation(scaleDown)
        animation.addAnimation(scaleUp)

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = spinnerAdapter

        val spinnerTextView = spinnerAdapter.getView(0, null, spinner) as? TextView
        spinnerTextView?.gravity = Gravity.CENTER

        fun generateGenreWord(topic: String): String {
            val genreWords = when (topic) {
                "Sci-Fi" -> arrayOf("Clone-Soldier", "Robot", "Android", "Mutant", "Netrunner")
                "Modern" -> arrayOf("Soldier", "Security Guard", "Scientist", "Policeman", "Doctor")
                "Fantasy" -> arrayOf("Knight", "Peasant", "King", "Thief", "Jester", "Footman", "Archer")
                "WW2" -> arrayOf("Gunner", "Grenadier", "Fire-Thrower", "Spy", "Officer")
                "Ancient" -> arrayOf("Slave", "Pharaoh", "Noble", "Fisherman", "Merchant")
                "Pre-Historic" -> arrayOf("Caveman", "Tribe-Leader", "Mammoth-Rider", "Hunter")
                else -> emptyArray()
            }
            return genreWords.random()
            }

        generateButton.setOnClickListener{
            generateButton.startAnimation(animation)

            val genres = spinner.selectedItem as String
            val genreWords = generateGenreWord(genres)
            generatedIdea.text = genreWords
            generatedIdea.rotationX = 180f

            generatedIdea.animate().apply {
                duration = 1000
                rotationXBy(540f)
            }
            if(generatedIdea.rotationX != 720f)
            {
                generatedIdea.rotationX = rotationXforIdea
            }

        }

    }
}