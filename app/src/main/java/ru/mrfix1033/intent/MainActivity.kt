package ru.mrfix1033.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var buttonOpenCalculator: Button
    lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonOpenCalculator = findViewById(R.id.buttonOpenCalculator)
        textViewResult = findViewById(R.id.textViewResult)

        buttonOpenCalculator.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            launchSomeActivity.launch(intent)
        }
    }

    private val launchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data!!
            textViewResult.text = data.getStringExtra("result")!!
        }
    }
}
