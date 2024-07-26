package com.hwr.containerdraggable

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class AvatarFloatView(context: Context) : BaseFloatView(context) {
    private var viewWidth: Int = LayoutParams.WRAP_CONTENT
    private var viewHeight: Int = LayoutParams.WRAP_CONTENT

    private var mAdsorbType = ADSORB_VERTICAL
    var isCanDrag: Boolean = true
    private var text: String = ""
    private var textonLine: String = ""
    private var textoffLine: String = ""
    private var textSize: Float = 0f
    private var textColor: Int = Color.BLACK

    private lateinit var textViewTitle: TextView
    private lateinit var textViewOnline: TextView
    private lateinit var textViewOffline: TextView
    private lateinit var containerView: ConstraintLayout

    init {
        addView(getChildView())
    }

    override fun getChildView(): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.float_view_item, null) as ConstraintLayout

        textViewTitle = view.findViewById(R.id.float_text_title)
        textViewTitle.apply {
            text = this@AvatarFloatView.text
            textSize = this@AvatarFloatView.textSize // 设置字体大小
            setTextColor(this@AvatarFloatView.textColor) // 设置文字颜色
        }

        textViewOnline = view.findViewById(R.id.tv_online_quantity)
        textViewOffline = view.findViewById(R.id.tv_offline_quantity)
        containerView = view.findViewById(R.id.float_view_container)

        view.layoutParams = ConstraintLayout.LayoutParams(viewWidth, viewHeight) // 设置视图大小
        return view
    }

    /*
    * 设置标题
    */
    fun setTitleText(text: String) {
        this.text = text
        updateView()
    }

    /*
    * 设置在线台数
    */
    fun setTextonLine(text: String) {
        this.textonLine = text
        updateView()
    }

    /*
    * 设置离线台数
    */
    fun setTextoffLine(text: String) {
        this.textoffLine = text
        updateView()
    }

    fun setTextSize(size: Float) {
        this.textSize = size
        updateView()
    }

    fun setTextColor(color: Int) {
        this.textColor = color
        updateView()
    }

    private fun updateView() {
        textViewTitle.let {
            it.text = this.text
            it.textSize = this.textSize
            it.setTextColor(this.textColor)
        }
        textViewOnline.let {
            it.text = this.textonLine
        }
        textViewOffline.let {
            it.text = this.textoffLine
        }

        containerView.let {
            val params = it.layoutParams
            params.width = viewWidth
            params.height = viewHeight
            it.layoutParams = params
        }
    }

    override fun getIsCanDrag(): Boolean = isCanDrag

    override fun getAdsorbType(): Int {
        return mAdsorbType
    }

    override fun getAdsorbTime(): Long {
        return 0
    }

    fun setAdsorbType(type: Int) {
        mAdsorbType = type
    }

    fun setIsCanDrag(canDrag: Boolean) {
        isCanDrag = canDrag
    }

    fun setViewSize(width: Int, height: Int) {
        viewWidth = width
        viewHeight = height
        updateView()
    }
}
