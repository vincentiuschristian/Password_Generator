package com.example.passwordgenerator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.security.SecureRandom

class MainActivity : AppCompatActivity() {

    private lateinit var tvPass: TextView
    private lateinit var btnCopy: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateBtn: Button = findViewById(R.id.btnGenerate)
        tvPass = findViewById(R.id.tvPassword)

        generateBtn.setOnClickListener { password(10) }

        btnCopy = findViewById(R.id.btnCopy)
        btnCopy.setOnClickListener {
            val text = tvPass.text.toString()
            copyToClipboard(this, text)
        }
    }

    private fun password(lenght: Int) {
        val pass: List<Char> = ('a'..'z').toList() + ('A'..'Z').toList() + ('0'..'9').toList()
        val random = SecureRandom()
        val shuffledPass = pass.shuffled(random)
        val pw = shuffledPass.take(lenght)

        tvPass.text = pw.joinToString("")
    }

    private fun copyToClipboard(context: Context, text: String) {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Copied text", text)
        clipboardManager.setPrimaryClip(clipData)
    }
}



