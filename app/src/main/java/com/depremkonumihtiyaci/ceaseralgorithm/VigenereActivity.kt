package com.depremkonumihtiyaci.ceaseralgorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.depremkonumihtiyaci.ceaseralgorithm.databinding.ActivityVigenereBinding

class VigenereActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVigenereBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVigenereBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button4.setOnClickListener {
            val plaintext = binding.plaintext.text.toString()
            val key = binding.keytext.text.toString()
            binding.textView2.text = "Şifrelenen ${vigenereEncrypt(plaintext,key)}"
        }
        binding.button5.setOnClickListener {
            binding.plaintext.text?.clear()
            binding.keytext.text?.clear()
            binding.textView2.text = ""
        }
        binding.button6.setOnClickListener {
            val ciphertext = binding.plaintext.text.toString()
            val key = binding.keytext.text.toString()
            binding.textView2.text = "Çözümlenen ${vigenereDecrypt(ciphertext,key)}"
        }
    }
    fun vigenereEncrypt(plaintext: String, key: String): String {
        val plaintextUpperCase = plaintext.toUpperCase()
        val keyUpperCase = key.toUpperCase()
        val sb = StringBuilder()

        var keyIndex = 0
        for (i in plaintextUpperCase.indices) {
            val plainChar = plaintextUpperCase[i]
            if (plainChar.isLetter()) {
                val keyChar = keyUpperCase[keyIndex % key.length]
                val shift = keyChar - 'A'
                val encryptedChar = ((plainChar + shift - 'A') % 26 + 'A'.toInt()).toChar()
                sb.append(encryptedChar)
                keyIndex++
            } else {
                sb.append(plainChar)
            }
        }
        return sb.toString()
    }

    fun vigenereDecrypt(ciphertext: String, key: String): String {
        val ciphertextUpperCase = ciphertext.toUpperCase()
        val keyUpperCase = key.toUpperCase()
        val sb = StringBuilder()

        var keyIndex = 0
        for (i in ciphertextUpperCase.indices) {
            val cipherChar = ciphertextUpperCase[i]
            if (cipherChar.isLetter()) {
                val keyChar = keyUpperCase[keyIndex % key.length]
                val shift = keyChar - 'A'
                val decryptedChar = ((cipherChar - shift - 'A' + 26) % 26 + 'A'.toInt()).toChar()
                sb.append(decryptedChar)
                keyIndex++
            } else {
                sb.append(cipherChar)
            }
        }
        return sb.toString()
    }
}