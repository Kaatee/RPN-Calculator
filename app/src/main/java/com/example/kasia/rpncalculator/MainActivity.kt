package com.example.kasia.rpncalculator


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.kasia.rpncalculator.R.id.changeSignButton
import com.example.kasia.rpncalculator.R.id.listView
import com.example.kasia.rpncalculator.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.pow
import java.lang.Math.sqrt
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var stackOfNumbers = ArrayList<Float>()
    var historyArray = ArrayList<ArrayList<Float>>()
    var tmp : String = "";
    var isFloat : Boolean = false
    var isTyping :Boolean = false

    var ListViewColor :String = "Czerwony"
    var FloatPrecision :Int =2
    var DarkButtons: Int = 0
    var isChanging: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)



        FloatPrecision = getIntent().getIntExtra("FloatPrecision", 2)
        DarkButtons = getIntent().getIntExtra("DarkButtons",0)
        isChanging= getIntent().getIntExtra("isChanging",0)

        if(isChanging==1)  {
            stackOfNumbers.addAll(getIntent().getSerializableExtra("stack") as ArrayList<Float>)
            historyArray.addAll(getIntent().getSerializableExtra("historyy") as ArrayList<ArrayList<Float>>)
            tmp = getIntent().getStringExtra("tmpp")
            textView1.text = tmp
        }

        val listView: ListView = findViewById<ListView>(R.id.listView)
        listView.adapter = MyCustomAdapter(this, stackOfNumbers,FloatPrecision)

        if(isChanging==1){

            if(DarkButtons==1){
                changeButtonToDark()
            }

            //val listView = findViewById<ListView>(R.id.listView)
            ListViewColor=  getIntent().getStringExtra("ListViewColor");

            //changing background color
            if(ListViewColor.equals("Szary")) {
                background1.setBackgroundColor(Color.GRAY)
            }
            if(ListViewColor.equals("Czerwony")) {
                background1.setBackgroundColor(Color.RED)
            }
            if(ListViewColor.equals("Niebieski")) {
                background1.setBackgroundColor(Color.BLUE)
            }
            if(ListViewColor.equals("Zielony")) {
                background1.setBackgroundColor(Color.GREEN)
            }
            if(ListViewColor.equals("Bialy")) {
                background1.setBackgroundColor(Color.WHITE)
            }
            if(ListViewColor.equals("Zolty")) {
                background1.setBackgroundColor(Color.YELLOW)
            }
        }


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
            tmp=tmp+"."
            textView1.text=tmp
        }


        cButton.setOnClickListener(){
            tmp=tmp.substring(startIndex = 0, endIndex = tmp.length-1)
            textView1.text = tmp
        }


        enterButton.setOnClickListener() {
            enter()
            textView1.text = ""
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers,FloatPrecision)
        }

        plusButton.setOnClickListener() {
            if(stackOfNumbers.size>1) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                var stackSize: Int = stackOfNumbers.size
                var sum: Float
                sum = stackOfNumbers.get(stackSize - 1) + stackOfNumbers.get(stackSize - 2)
                stackOfNumbers.removeAt(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 2)

                stackOfNumbers.add(sum.toFloat())
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz liczbe do dodania", Toast.LENGTH_LONG).show();


        }

        minusButton.setOnClickListener() {
            if(stackOfNumbers.size>1) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                var stackSize: Int = stackOfNumbers.size
                var dif: Float
                dif = stackOfNumbers.get(stackSize - 2) - stackOfNumbers.get(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 2)
                stackOfNumbers.add(dif)
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz kolejna liczbe na stos", Toast.LENGTH_LONG).show();

        }

        multiplicateButton.setOnClickListener() {
            if(stackOfNumbers.size > 1) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                var stackSize: Int = stackOfNumbers.size
                var result: Float
                result = stackOfNumbers.get(stackSize - 2) * stackOfNumbers.get(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 2)

                stackOfNumbers.add(result.toFloat())
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz kolejna liczbe na stos", Toast.LENGTH_LONG).show();
        }

        devButton.setOnClickListener() {
            if(stackOfNumbers.size >1) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                var stackSize: Int = stackOfNumbers.size
                var result: Float
                result = stackOfNumbers.get(stackSize - 2) / stackOfNumbers.get(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 1)
                stackOfNumbers.removeAt(stackSize - 2)

                stackOfNumbers.add(result)
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz kolejna liczbe na stos", Toast.LENGTH_LONG).show();
        }

        powButton.setOnClickListener() {
            if (stackOfNumbers.size > 1){
            var historyTmp = ArrayList<Float>()
            historyTmp = stackOfNumbers.clone() as ArrayList<Float>
            historyArray.add(historyTmp)

            var stackSize: Int = stackOfNumbers.size
            var result: Double
            result = pow((stackOfNumbers.get(stackSize - 2)).toDouble(), (stackOfNumbers.get(stackSize - 1)).toDouble())
            stackOfNumbers.removeAt(stackSize - 1)
            stackOfNumbers.removeAt(stackSize - 2)

            stackOfNumbers.add(result.toFloat())
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
        }
        else
            Toast.makeText(getApplicationContext(), "Wpisz kolejna liczbe na stos", Toast.LENGTH_LONG).show();
        }

        sqrtButton.setOnClickListener() {
            if(stackOfNumbers.size >0) {
                if(stackOfNumbers.get(stackOfNumbers.size-1) < 0)
                    Toast.makeText(getApplicationContext(), "Nie mozna wyjac pierwiastka z liczby ujemnej", Toast.LENGTH_LONG).show();
                else {
                    var historyTmp = ArrayList<Float>()
                    historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                    historyArray.add(historyTmp)

                    var stackSize: Int = stackOfNumbers.size
                    var result: Double
                    result = sqrt((stackOfNumbers.get(stackSize - 1)).toDouble())
                    stackOfNumbers.removeAt(stackSize - 1)

                    stackOfNumbers.add(result.toFloat())
                    val listView = findViewById<ListView>(R.id.listView)
                    listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
                }
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz liczbe na stos", Toast.LENGTH_LONG).show();
        }

        dropButton.setOnClickListener() {
            if(stackOfNumbers.size >0) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                stackOfNumbers.removeAt(stackOfNumbers.size - 1)
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz liczbe na stos", Toast.LENGTH_LONG).show();
        }

        swapButton.setOnClickListener() {
            if(stackOfNumbers.size >1) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                val tmp1: Float = stackOfNumbers.get(stackOfNumbers.size - 1)
                val tmp2: Float = stackOfNumbers.get(stackOfNumbers.size - 2)
                stackOfNumbers.removeAt(stackOfNumbers.size - 1)
                stackOfNumbers.removeAt(stackOfNumbers.size - 1)
                stackOfNumbers.add(tmp1)
                stackOfNumbers.add(tmp2)
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz liczbe na stos", Toast.LENGTH_LONG).show();
        }

        acButton.setOnClickListener() {

            var historyTmp = ArrayList<Float>()
            historyTmp = stackOfNumbers.clone() as ArrayList<Float>
            historyArray.add(historyTmp)

            stackOfNumbers.clear()
            val listView = findViewById<ListView>(R.id.listView)
            listView.adapter = MyCustomAdapter(this, stackOfNumbers,FloatPrecision)
        }

        changeSignButton.setOnClickListener() {
            if(stackOfNumbers.size >0) {
                var historyTmp = ArrayList<Float>()
                historyTmp = stackOfNumbers.clone() as ArrayList<Float>
                historyArray.add(historyTmp)

                val tmp: Float = stackOfNumbers.get(stackOfNumbers.size - 1)
                stackOfNumbers.removeAt(stackOfNumbers.size - 1)
                stackOfNumbers.add((-1) * tmp)
                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Wpisz liczbe na stos", Toast.LENGTH_LONG).show();
        }

        undoButton.setOnClickListener() {
            if(historyArray.size>0) {
                stackOfNumbers.clear()
                //Toast.makeText(getApplicationContext(), "Hist Array : " + historyArray, Toast.LENGTH_LONG).show();
                stackOfNumbers.addAll(historyArray.get(historyArray.size - 1))
                historyArray.removeAt(historyArray.size - 1)

                val listView = findViewById<ListView>(R.id.listView)
                listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
            }
            else
                Toast.makeText(getApplicationContext(), "Nie posiadasz historii", Toast.LENGTH_LONG).show();

        }

        settingsButton.setOnClickListener() {
            val intent = Intent(this, SettingsActivity::class.java )
            intent.putExtra("StackOfNumbers", stackOfNumbers)
            intent.putExtra("Tmp",tmp)
            intent.putExtra("History",historyArray)
            startActivity(intent)
        }


    }

    override public fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("Tmp1", tmp)
        outState?.putSerializable("StackOfNumbers1", stackOfNumbers)
        outState?.putSerializable("History1", historyArray)
        super.onSaveInstanceState(outState)
    }

    override public fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        stackOfNumbers  = savedInstanceState!!.getSerializable("StackOfNumbers1") as ArrayList<Float>
        historyArray =savedInstanceState!!.getSerializable("History1") as ArrayList<ArrayList<Float>>
        tmp  = savedInstanceState!!.getString("Tmp1")

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = MyCustomAdapter(this, stackOfNumbers, FloatPrecision)
        textView1.text=tmp

    }


    override public fun onDestroy() {
        super.onDestroy()
    }

    override public fun onPause() {
        super.onPause()
    }

    private class MyCustomAdapter(context: Context, list: ArrayList<Float>,FloatPrecision: Int): BaseAdapter() {

        private val nfloatPrec : Int
        private val nContext: Context
        private val nlist: ArrayList<Float>
        init{
            nContext = context
            nlist = list
            nfloatPrec = FloatPrecision

        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(nContext)
            var x : String= nlist.get(position).toString()
            x=cutToProperPrecision(x)
            var y: Float = x.toFloat()
            //textView.text = "$position: ${nlist.get(position)}"
            textView.text = "$position: ${y}"

            return textView
        }

        fun cutToProperPrecision(tmp1:String): String{
            var tmp2: String =tmp1
            if(tmp1.contains(".")){
                var indexOfDot:Int = tmp1.indexOf(".")
                if(indexOfDot+nfloatPrec < tmp1.length){
                    var endIdx: Int = indexOfDot+nfloatPrec+1
                    tmp2 =tmp1.substring(startIndex = 0, endIndex = endIdx)
                }
            }

            return tmp2
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
           }

        override fun getCount(): Int {
            return nlist.size
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

    fun changeButtonToDark(){
        plusButton.setBackgroundColor(Color.parseColor("#444444"))
        plusButton.setTextColor(Color.parseColor("#ffffff"))
        minusButton.setBackgroundColor(Color.parseColor("#444444"))
        minusButton.setTextColor(Color.parseColor("#ffffff"))
        devButton.setBackgroundColor(Color.parseColor("#444444"))
        devButton.setTextColor(Color.parseColor("#ffffff"))
        multiplicateButton.setBackgroundColor(Color.parseColor("#444444"))
        multiplicateButton.setTextColor(Color.parseColor("#ffffff"))
        sqrtButton.setBackgroundColor(Color.parseColor("#444444"))
        sqrtButton.setTextColor(Color.parseColor("#ffffff"))
        cButton.setBackgroundColor(Color.parseColor("#444444"))
        cButton.setTextColor(Color.parseColor("#ffffff"))
        button0.setBackgroundColor(Color.parseColor("#444444"))
        button0.setTextColor(Color.parseColor("#ffffff"))
        button1.setBackgroundColor(Color.parseColor("#444444"))
        button1.setTextColor(Color.parseColor("#ffffff"))
        button2.setBackgroundColor(Color.parseColor("#444444"))
        button2.setTextColor(Color.parseColor("#ffffff"))
        button3.setBackgroundColor(Color.parseColor("#444444"))
        button3.setTextColor(Color.parseColor("#ffffff"))
        button4.setBackgroundColor(Color.parseColor("#444444"))
        button4.setTextColor(Color.parseColor("#ffffff"))
        button5.setBackgroundColor(Color.parseColor("#444444"))
        button5.setTextColor(Color.parseColor("#ffffff"))
        button6.setBackgroundColor(Color.parseColor("#444444"))
        button6.setTextColor(Color.parseColor("#ffffff"))
        button7.setBackgroundColor(Color.parseColor("#444444"))
        button7.setTextColor(Color.parseColor("#ffffff"))
        button8.setBackgroundColor(Color.parseColor("#444444"))
        button8.setTextColor(Color.parseColor("#ffffff"))
        button9.setBackgroundColor(Color.parseColor("#444444"))
        button9.setTextColor(Color.parseColor("#ffffff"))
        dropButton.setBackgroundColor(Color.parseColor("#444444"))
        dropButton.setTextColor(Color.parseColor("#ffffff"))
        enterButton.setBackgroundColor(Color.parseColor("#444444"))
        enterButton.setTextColor(Color.parseColor("#ffffff"))
        settingsButton.setBackgroundColor(Color.parseColor("#444444"))
        settingsButton.setTextColor(Color.parseColor("#ffffff"))
        swapButton.setBackgroundColor(Color.parseColor("#444444"))
        swapButton.setTextColor(Color.parseColor("#ffffff"))
        acButton.setBackgroundColor(Color.parseColor("#444444"))
        acButton.setTextColor(Color.parseColor("#ffffff"))
        dotButton.setBackgroundColor(Color.parseColor("#444444"))
        dotButton.setTextColor(Color.parseColor("#ffffff"))
        changeSignButton.setBackgroundColor(Color.parseColor("#444444"))
        changeSignButton.setTextColor(Color.parseColor("#ffffff"))
        powButton.setBackgroundColor(Color.parseColor("#444444"))
        powButton.setTextColor(Color.parseColor("#ffffff"))
        undoButton.setBackgroundColor(Color.parseColor("#444444"))
        undoButton.setTextColor(Color.parseColor("#ffffff"))
    }








}
