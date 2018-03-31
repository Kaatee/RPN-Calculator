package com.example.kasia.rpncalculator


import android.content.Context
import android.content.Intent
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
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    //val listView = findViewById<ListView>(R.id.listView)
    var stackOfNumbers = ArrayList<Float>()
    val historyArray = ArrayList<ArrayList<Float>>()
    var tmp : String = "";
    var isFloat : Boolean = false
    var isTyping :Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        //val textView = findViewById<ListView>(R.id.textView)

        button0.setOnClickListener() {
            makeNum(0)
        }

        button1.setOnClickListener() {
            makeNum(1)
        }

        button2.setOnClickListener() {
            makeNum(2)
        }

        button3.setOnClickListener() {
            makeNum(3)
        }

        button4.setOnClickListener() {
            makeNum(4)
        }

        button5.setOnClickListener() {
            makeNum(5)
        }

        button6.setOnClickListener() {
            makeNum(6)
        }

        button7.setOnClickListener() {
            makeNum(7)
        }

        button8.setOnClickListener() {
            makeNum(8)
        }

        button9.setOnClickListener() {
            makeNum(9)
        }

        dotButton.setOnClickListener(){
            Toast.makeText(getApplicationContext(), "Kliknales: .", Toast.LENGTH_LONG).show();
            tmp=tmp+"."
            textView1.text=tmp
        }


        cButton.setOnClickListener(){
            Toast.makeText(getApplicationContext(), "Kliknales: C", Toast.LENGTH_LONG).show();
            tmp=tmp.substring(startIndex = 0, endIndex = tmp.length-1)

            textView1.text = tmp
        }


        enterButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: ENTER", Toast.LENGTH_LONG).show();
            enter()
            textView1.text = ""
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        plusButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: +", Toast.LENGTH_LONG).show();
            historyArray.add(stackOfNumbers)
            Toast.makeText(getApplicationContext(), "Hist Arr : "+historyArray, Toast.LENGTH_LONG).show();
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
            historyArray.add(stackOfNumbers)
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
            historyArray.add(stackOfNumbers)
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
            historyArray.add(stackOfNumbers)
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
            historyArray.add(stackOfNumbers)
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
            historyArray.add(stackOfNumbers)
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
            historyArray.add(stackOfNumbers)
            stackOfNumbers.removeAt(stackOfNumbers.size-1)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        swapButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: SWAP", Toast.LENGTH_LONG).show();
            historyArray.add(stackOfNumbers)
            val tmp1: Float = stackOfNumbers.get(stackOfNumbers.size-1)
            val tmp2: Float = stackOfNumbers.get(stackOfNumbers.size-2)
            stackOfNumbers.removeAt(stackOfNumbers.size-1)
            stackOfNumbers.removeAt(stackOfNumbers.size-1)
            stackOfNumbers.add(tmp1)
            stackOfNumbers.add(tmp2)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        acButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: AC", Toast.LENGTH_LONG).show();
            historyArray.add(stackOfNumbers)
            stackOfNumbers.clear()
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        changeSignButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: +-", Toast.LENGTH_LONG).show();
            historyArray.add(stackOfNumbers)
            val tmp : Float = stackOfNumbers.get(stackOfNumbers.size-1)
            stackOfNumbers.removeAt(stackOfNumbers.size-1)
            stackOfNumbers.add((-1)*tmp)
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)
        }

        undoButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: UNDO", Toast.LENGTH_LONG).show();
            stackOfNumbers.clear()
            Toast.makeText(getApplicationContext(), "Hist Arr : "+historyArray, Toast.LENGTH_LONG).show();
            stackOfNumbers.addAll(historyArray.get(historyArray.size-1))
            //Toast.makeText(getApplicationContext(), "Kliknales: UNDO : "+stackOfNumbers, Toast.LENGTH_LONG).show();
            historyArray.removeAt(historyArray.size-1)

            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers)

        }

        settingsButton.setOnClickListener() {
            Toast.makeText(getApplicationContext(), "Kliknales: SETTINGS", Toast.LENGTH_LONG).show();
            val intent = Intent(this, SettingsActivity::class.java )
            startActivity(intent)
        }


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
        tmp=tmp+num.toString()
        isTyping = true
        textView1.text=tmp
    }

    fun enter(){
        if(isTyping){
            if(tmp.endsWith(".")){
                tmp=tmp+"0"
            }
            stackOfNumbers.add(tmp.toFloat())
            isTyping = false
            tmp=""
        }
        else{
            stackOfNumbers.add(stackOfNumbers.get(stackOfNumbers.size-1))
        }

    }






}
