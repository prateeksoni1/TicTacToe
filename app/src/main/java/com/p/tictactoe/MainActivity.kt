package com.p.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var zeroCount = 0
    var crossCount = 0
    var count = 0

    var values = arrayOf("", "", "", "", "", "", "", "", "")

    override fun onClick(view: View?) {
        val id = view!!.id
        var value: String? = null
        var resId: Int = 0
        when(turn) {
            1 -> {
                resId = R.drawable.zero
                value = "O"
            }

            2 -> {
                resId = R.drawable.cross
                value = "X"
            }
            else -> print("Impossible shit")
        }

        if (!checkIfPresent(id)) {
            count++

            val imageView = findViewById<ImageView>(id)
            imageView.setImageResource(resId)
            var index: Int? = null
            when (id) {
                R.id.box00 -> index = 0
                R.id.box01 -> index = 1
                R.id.box02 -> index = 2
                R.id.box10 -> index = 3
                R.id.box11 -> index = 4
                R.id.box12 -> index = 5
                R.id.box20 -> index = 6
                R.id.box21 -> index = 7
                R.id.box22 -> index = 8
            }
            if (turn == 1) zeroCount++
            else crossCount++

            if (index != null) values[index] = value!!


            if (zeroCount >= 3 || crossCount >= 3) {
                if (checkForWin(turn)) {
                    disableButtons(true)
                    showResult(turn)
                }
            }

            if (count >= 9) {
                Log.d("GAME", "Finished")
                disableButtons(true)
                showResult(-1)
            }

            turn = if (turn == 1) 2 else 1
            Log.d("TURN", "Turn changed")
        }

    }

    private fun showResult(turn: Int) {
        var result = ""
        when(turn) {
            1 -> result = "O WINS"
            2 -> result = "X WINS"
            else -> result = "DRAW :)"
        }
        resultTxt.text = result
    }

    private fun disableButtons(status: Boolean) {
        box00.isEnabled = !status
        box01.isEnabled = !status
        box02.isEnabled = !status
        box10.isEnabled = !status
        box11.isEnabled = !status
        box12.isEnabled = !status
        box20.isEnabled = !status
        box21.isEnabled = !status
        box22.isEnabled = !status
    }

    private fun checkForWin(turn: Int) : Boolean {

        if (
                (values[0] != "" && values[0] == values[1] && values[1] == values[2]) ||
                (values[0] != "" && values[0] == values[4] && values[4] == values[8]) ||
                (values[0] != "" && values[0] == values[3] && values[3] == values[6]) ||
                (values[3] != "" && values[3] == values[4] && values[4] == values[5]) ||
                (values[6] != "" && values[6] == values[7] && values[7] == values[8]) ||
                (values[3] != "" && values[3] == values[4] && values[4] == values[5]) ||
                (values[1] != "" && values[1] == values[4] && values[4] == values[7]) ||
                (values[2] != "" && values[2] == values[5] && values[5] == values[8]) ||
                (values[2] != "" && values[2] == values[4] && values[4] == values[6])
        ) {
            Log.d("WIN", "$turn wins")
            return true
        } else {
            Log.d("LOSE", "RIP")
            return false
        }

    }

    private fun checkIfPresent(id: Int) : Boolean{
        val imageView = findViewById<ImageView>(id)
        return imageView.drawable != null
    }


    var turn = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        box00.setOnClickListener(this)
        box01.setOnClickListener(this)
        box02.setOnClickListener(this)
        box10.setOnClickListener(this)
        box11.setOnClickListener(this)
        box12.setOnClickListener(this)
        box20.setOnClickListener(this)
        box21.setOnClickListener(this)
        box22.setOnClickListener(this)


        restartBtn.setOnClickListener {
            clearShit()

        }


    }

    fun clearShit() {
       recreate()
    }


}
