package ru.mrfix1033.intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    lateinit var editTextFirstOperand: EditText
    lateinit var editTextSecondOperand: EditText
    lateinit var textViewResult: TextView
    lateinit var buttonPlus: Button
    lateinit var buttonMinus: Button
    lateinit var buttonMultiply: Button
    lateinit var buttonDivide: Button
    lateinit var buttonTransferData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        editTextFirstOperand = findViewById(R.id.editTextFirstOperand)
        editTextSecondOperand = findViewById(R.id.editTextSecondOperand)
        textViewResult = findViewById(R.id.textViewResult)
        buttonPlus = findViewById(R.id.buttonPlus)
        buttonMinus = findViewById(R.id.buttonMinus)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        buttonTransferData = findViewById(R.id.buttonTransferData)

        buttonPlus.setOnClickListener(createButtonOperateFunction(1))
        buttonMinus.setOnClickListener(createButtonOperateFunction(2))
        buttonMultiply.setOnClickListener(createButtonOperateFunction(3))
        buttonDivide.setOnClickListener(createButtonOperateFunction(4))

        buttonTransferData.setOnClickListener {
            intent = Intent()
            intent.putExtra("result", textViewResult.text)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun createButtonOperateFunction(mode: Int) = { _: Any ->
        textViewResult.text = run {
            val first = editTextFirstOperand.text.toString().toDoubleOrNull()
            val second = editTextSecondOperand.text.toString().toDoubleOrNull()
            if (first == null || second == null || mode == 4 && second == 0.0) "Ошибка"
            else first.run {
                val operate: (Double) -> Double =
                    when (mode) {
                        1 -> ::plus
                        2 -> ::minus
                        3 -> ::times
                        4 -> ::div
                        else -> throw RuntimeException("Incorrect mode $mode")
                    }
                operate(second).toString()
            }
        }
    }
}