package com.hwr.containerdraggable


import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mFloatView: AvatarFloatView? = null
    private val floatViewList = mutableListOf<AvatarFloatView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        initView()
    }


    fun initView() {

        val btnHideFloat = findViewById<Button>(R.id.btn_hide_float)
        val radioGroup = findViewById<RadioGroup>(R.id.radio_group)
        val radioLeftRight = findViewById<RadioButton>(R.id.radio_left_right)
        val radioTopBottom = findViewById<RadioButton>(R.id.radio_top_bottom)
        val radioCanDrag = findViewById<Button>(R.id.btn_show_float_candrag)
        val radioIsCanDrag = findViewById<Button>(R.id.btn_show_float_iscandrag)
        val textView = findViewById<TextView>(R.id.textView)
        val btnAllLeft = findViewById<Button>(R.id.btn_all_left)
        val btnAllTop = findViewById<Button>(R.id.btn_all_top)

        btnAllTop.setOnClickListener {
            floatViewList.forEach { floatView ->
                floatView.setAdsorbType(BaseFloatView.ADSORB_VERTICAL)
            }
        }

        btnAllLeft.setOnClickListener {
            floatViewList.forEach { floatView ->
                floatView.setAdsorbType(BaseFloatView.ADSORB_HORIZONTAL)
            }
        }

        radioCanDrag.setOnClickListener {
            floatViewList.forEach { floatView ->
                floatView.setIsCanDrag(false)
            }
        }
        radioIsCanDrag.setOnClickListener {
            floatViewList.forEach { floatView ->
                floatView.setIsCanDrag(true)
            }
        }

        textView.setOnClickListener {
            val floatView = AvatarFloatView(this)
            floatView.setTitleText("Your Dynamic Text")
            floatView.setTextonLine("1")
            floatView.setTextoffLine("2")
            floatView.setTextSize(23f)
            floatView.setTextColor(Color.BLACK)
            floatView.setViewSize(300,150)
            floatView.setIsCanDrag(true)
            floatView.setAdsorbType(BaseFloatView.ADSORB_HORIZONTAL)

            FloatManager.with(this).add(floatView).show()
            floatViewList.add(floatView)
        }


        btnHideFloat.setOnClickListener {
            FloatManager.hide()
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.radio_left_right) {
                mFloatView?.setAdsorbType(BaseFloatView.ADSORB_HORIZONTAL)
            } else {
                mFloatView?.setAdsorbType(BaseFloatView.ADSORB_VERTICAL)
            }
        }
    }


}