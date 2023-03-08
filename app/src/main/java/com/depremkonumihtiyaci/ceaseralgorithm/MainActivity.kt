package com.depremkonumihtiyaci.ceaseralgorithm

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.depremkonumihtiyaci.ceaseralgorithm.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val text = binding.textYazi.text
            val shift = binding.shiftText.text.toString()
            binding.textView.text = "Şifrelenen ${encrypt(text.toString(), shift.toInt())}"

        }
        binding.button2.setOnClickListener {
            val text = binding.textYazi.text
            val shift = binding.shiftText.text.toString()
            binding.textView.text = "Çözülen ${decrypt(text.toString(), shift.toInt())}"
        }
        binding.textView.setOnClickListener {
            val cm: ClipboardManager =
                applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setText(binding.textView.getText())
            Toast.makeText(applicationContext, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
        binding.button3.setOnClickListener {
            binding.textYazi.text?.clear()
            binding.shiftText.text?.clear()
            binding.textView.text = ""
        }
    }
    fun encrypt(text: String, shift: Int): String {
        var result = ""

        for (i in 0 until text.length) {
            val character = text[i]

            if (character.isLetter()) {
                val ascii = character.toInt()
                val shiftedAscii = (ascii + shift - if (character.isUpperCase()) 65 else 97) % 26 + if (character.isUpperCase()) 65 else 97
                result += shiftedAscii.toChar()
            } else {
                result += character
            }
        }

        return result
    }

    fun decrypt(text: String, shift: Int): String {
        var result = ""

        for (i in 0 until text.length) {
            val character = text[i]

            if (character.isLetter()) {
                val ascii = character.toInt()
                val shiftedAscii = (ascii - shift - if (character.isUpperCase()) 65 else 97 + 26) % 26 + if (character.isUpperCase()) 65 else 97
                result += shiftedAscii.toChar()
            } else {
                result += character
            }
        }

        return result
    }
}