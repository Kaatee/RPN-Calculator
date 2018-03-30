package com.example.kasia.rpncalculator


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.kasia.rpncalculator.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.pow
import java.lang.Math.sqrt
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    //val listView = findViewById<ListView>(R.id.listView)
    var stackOfNumbers = ArrayList<Float>()
    var tmp : Int = 0;
    var tmpFloat: Int = 0
    var isFloat : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        button0.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 0", Toast.LENGTH_LONG).show();
            makeNum(0)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button1.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 1", Toast.LENGTH_LONG).show();
            makeNum(1)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button2.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 2", Toast.LENGTH_LONG).show();
            makeNum(2)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button3.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 3", Toast.LENGTH_LONG).show();
            makeNum(3)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button4.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 4", Toast.LENGTH_LONG).show();
            makeNum(4)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button5.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 5", Toast.LENGTH_LONG).show();
            makeNum(5)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button6.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 6", Toast.LENGTH_LONG).show();
            makeNum(6)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button7.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 7", Toast.LENGTH_LONG).show();
            makeNum(7)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button8.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 8", Toast.LENGTH_LONG).show();
            makeNum(8)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        button9.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: 9", Toast.LENGTH_LONG).show();
            makeNum(9)
            Toast.makeText(getApplicationContext(), "Kliknales: "+ tmp+"."+tmpFloat, Toast.LENGTH_LONG).show();
        }

        dotButton.setOnClickListener(){
            Toast.makeText(getApplicationContext(), "Kliknales: .", Toast.LENGTH_LONG).show();
            isFloat = true
        }

        enterButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: ENTER", Toast.LENGTH_LONG).show();
            var x:String = tmp.toString()+"."+tmpFloat.toString()
            var y: Float = x.toFloat()
            stackOfNumbers.add(y)
            tmp = 0;
            tmpFloat = 0
            isFloat = false
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        plusButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: +", Toast.LENGTH_LONG).show();
            var stackSize: Int = stackOfNumbers.size
            var sum: Float
            sum = stackOfNumbers.get(stackSize-1) + stackOfNumbers.get(stackSize-2)
            stackOfNumbers.removeAt(stackSize-1)
            stackOfNumbers.removeAt(stackSize-2)
            stackOfNumbers.add(sum)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)

        }

        minusButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: -", Toast.LENGTH_LONG).show();
            var stackSize: Int = stackOfNumbers.size
            var dif: Float
            dif = stackOfNumbers.get(stackSize-2) - stackOfNumbers.get(stackSize-1)
            stackOfNumbers.removeAt(stackSize-1)
            stackOfNumbers.removeAt(stackSize-2)
            stackOfNumbers.add(dif)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        multiplicateButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: *", Toast.LENGTH_LONG).show();
            var stackSize: Int = stackOfNumbers.size
            var result: Float
            result = stackOfNumbers.get(stackSize-2) * stackOfNumbers.get(stackSize-1)
            stackOfNumbers.removeAt(stackSize-1)
            stackOfNumbers.removeAt(stackSize-2)
            stackOfNumbers.add(result)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        devButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: /", Toast.LENGTH_LONG).show();
            var stackSize: Int = stackOfNumbers.size
            var result: Float
            result = stackOfNumbers.get(stackSize-2) / stackOfNumbers.get(stackSize-1)
            stackOfNumbers.removeAt(stackSize-1)
            stackOfNumbers.removeAt(stackSize-2)
            stackOfNumbers.add(result)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        powButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: pow", Toast.LENGTH_LONG).show();
            var stackSize: Int = stackOfNumbers.size
            var result: Double
            result = pow((stackOfNumbers.get(stackSize-2)).toDouble(), (stackOfNumbers.get(stackSize-1)).toDouble())
            stackOfNumbers.removeAt(stackSize-1)
            stackOfNumbers.removeAt(stackSize-2)
            stackOfNumbers.add(result.toFloat())
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        sqrtButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: sqrt", Toast.LENGTH_LONG).show();
            var stackSize: Int = stackOfNumbers.size
            var result: Double
            result = sqrt((stackOfNumbers.get(stackSize-1)).toDouble())
            stackOfNumbers.removeAt(stackSize-1)
            stackOfNumbers.add(result.toFloat())
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        dropButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: DROP", Toast.LENGTH_LONG).show();
        }

        swapButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: SWAP", Toast.LENGTH_LONG).show();
        }

        acButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: AC", Toast.LENGTH_LONG).show();
        }

        changeSignButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: +-", Toast.LENGTH_LONG).show();
        }

        undoButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: UNDO", Toast.LENGTH_LONG).show();
        }

        settingsButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: SETTINGS", Toast.LENGTH_LONG).show();
        }

        //val listView = findViewById<ListView>(R.id.listView)
        //listView.adapter = MyCustomAdapter(this, stackOfNumbers) //

    }
    private class MyCustomAdapter(context: Context, list: ArrayList<Float>): BaseAdapter() {

        private val nContext: Context
        private val nlist: ArrayList<Float>
        init{
            nContext = context
            nlist = list

        }
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(nContext)
            //textView.text = "Row for ListView $position"
            textView.text = "$position: ${nlist.get(position)}"
            return textView
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
           }

        override fun getCount(): Int {
            //return 5
            return nlist.size
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    fun makeNum(num: Int){
        if(isFloat) {
            tmpFloat = addNumber(tmpFloat,num)
        } else {
            tmp = addNumber(tmp,num)
        }
    }
    fun addNumber(oryginal: Int, numberToAdd: Int ): Int{
            val str = oryginal.toString()
            val outStr = str + numberToAdd.toString()
            val x = outStr.toInt()
            return x

    }





}
