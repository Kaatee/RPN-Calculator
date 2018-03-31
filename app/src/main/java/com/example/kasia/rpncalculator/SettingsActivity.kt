package com.example.kasia.rpncalculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    lateinit var option: Spinner
    lateinit var editText2 : EditText
    var color: String = ""
    var floatPrecision : Int = 2 //default
    var darkButtons : Int = 0 // !!!!!!!!!!!!!!!!! ustawić !!!!!!!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //spinner handle
        option = findViewById(R.id.spinner)
        val colors = arrayOf("Czerwony","Niebieski","Zielony","Bialy","Szary","Czarny")
        option.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colors)

        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                color = colors.get(position)
            }
        }


        editText2 = findViewById(R.id.editText2)

        zastosujButton.setOnClickListener() {
            floatPrecision = (editText2.getText().toString()).toInt()
            Toast.makeText(getApplicationContext(), "FloatPrecision"+floatPrecision, Toast.LENGTH_LONG).show();
            val intent = Intent(this, MainActivity::class.java )
            intent.putExtra("ListViewColor", color)
            intent.putExtra("FloatPrecision", floatPrecision)
            intent.putExtra("DarkButtons", darkButtons)
            intent.putExtra("isChanging", 1)
            startActivity(intent)
        }


    }





}
