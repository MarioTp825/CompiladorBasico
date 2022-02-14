package edu.uni.compiladorbasico

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.text.Text

class HelloController {
    @FXML
    private lateinit var tCodeOutput: Text

    @FXML
    private lateinit var taCodeEditor: TextArea

    @FXML
    fun compileCode(e: ActionEvent) {
        tCodeOutput.text = "Salida: \n${compile(taCodeEditor.text)}"
    }

    @FXML
    private fun clearCode(e: ActionEvent) {
        taCodeEditor.clear()
        taCodeEditor.requestFocus()
    }

    private fun compile(code: String) = "Código:\n$code \n${stepOne(code)}"

    private fun stepOne(code: String): String {
        val words = mutableListOf<String>()
        val sign = mutableListOf<String>()
        val ids = mutableListOf<Int>()
        var pos = 0
        for (letter in code.trim()) {
            if(letter.let { !it.isLetter() && !it.isDigit() }) {
                sign.add(letter.toString())
                pos++
                continue
            }

            if(pos < words.size) {
                words[pos] += letter.toString()
            } else {
                words.add(letter.toString())
                ids.add(pos + 1)
            }
        }
        var content = ""
        for (i in 0 until words.size) {
            content += words[i].let { if(it.isNum()) "<$it>" else "<$i,$it>" } +
                    if(i < sign.size) " ${sign[i]} " else ""
        }
        return "Primer Paso:\n$content"
    }

    private fun String.isNum(): Boolean = this
        .removePrefix("-")
        .removePrefix("+")
        .all { it in '0'..'9' }
}