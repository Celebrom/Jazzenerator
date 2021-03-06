package com.example.jazzenerator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var timer = Timer()
        val defaultSeconds = "10"

        val actionBar = supportActionBar
        actionBar!!.title = "Jazzenerator"

        val notes = hashMapOf(
            0 to "Ab",
            1 to "A",
            2 to "Bb",
            3 to "B",
            4 to "C",
            5 to "C#",
            6 to "D",
            7 to "Eb",
            8 to "E",
            9 to "F",
            10 to "F#",
            11 to "G"
        )
        val positions = hashSetOf("Position A", "Position B")
        val extensions = hashSetOf("-7", "7", "maj7", "-7b5", "7b9", "7#5#9")

        fun generateExtension(): String {
            if (extensions.isEmpty()) return ("")
            return (extensions.elementAt(Random.nextInt(until = extensions.size)).toString())
        }

        fun generateNote(): String {
            if (notes.isEmpty()) return ("")
            return (notes[Random.nextInt(until = 12)].toString())
        }

        fun generatePosition(): String {
            if (positions.isEmpty()) return ("")
            return (positions.elementAt(Random.nextInt(until = positions.size)).toString())
        }

        fun generateChord() {
            chordTextView.text = generateNote()
            extensionTextView.text = generateExtension()
            positionTextView.text = generatePosition()
        }

        positionA.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) positions.add("Position A")
            else positions.remove("Position A")
        }

        positionB.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) positions.add("Position B")
            else positions.remove("Position B")
        }

        min7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) extensions.add("-7")
            else extensions.remove("-7")
        }

        dom7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) extensions.add("7")
            else extensions.remove("7")
        }

        maj7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) extensions.add("maj7")
            else extensions.remove("maj7")
        }

        dim7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) extensions.add("-7b5")
            else extensions.remove("-7b5")
        }

        maj7b9.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) extensions.add("7b9")
            else extensions.remove("7b9")
        }

        aug7.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) extensions.add("7#5#9")
            else extensions.remove("7#5#9")
        }

        randomizedButton.setOnClickListener {
            generateChord()
        }

        timerCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (secondsInput.text.isNullOrEmpty())
                    secondsInput.setText(defaultSeconds)

                val milliseconds = secondsInput.text.toString().toLong() * 1000
                timer = Timer()
                timer.scheduleAtFixedRate(0, milliseconds) { generateChord() }
            } else
                timer.cancel()
        }
    }
}



/*
TODO LIST

- Connecter a GIT

- Ajouter la posibilité de mettre symbol au lieu de texte: Cmaj7 vs C(triangle)7
https://tamingthesaxophone.com/jazz-chord-symbols
https://en.wikipedia.org/wiki/Musical_Symbols_(Unicode_block)
https://en.wikipedia.org/wiki/List_of_Unicode_characters

- Permettre d'enlever des notes
- Créer un menu
- Créer des settings
- Faire une page II - V - I comme en ce moment + settings

* /
