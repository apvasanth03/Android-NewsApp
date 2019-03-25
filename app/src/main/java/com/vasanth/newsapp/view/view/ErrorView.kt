package com.vasanth.newsapp.view.view

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.vasanth.newsapp.R


/**
 * A Class used to provide view for error states (like NoIntrnetConnection, NoResult etc)
 *
 * @author Vasanth
 */
class ErrorView(context: Context, @DrawableRes icon: Int, title: String?, message: String?) : FrameLayout(context) {

    private val ivIcon: ImageView
    private val tvTitle: TextView
    private val tvMessage: TextView

    init {
        val params =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams = params
        View.inflate(context, R.layout.view_error, this)
        ivIcon = findViewById(R.id.icon)
        tvTitle = findViewById(R.id.title)
        tvMessage = findViewById(R.id.message)

        populateView(icon, title, message)
    }

    // PUBLIC METHODS.
    fun updateView(@DrawableRes icon: Int, title: String?, message: String?) {
        populateView(icon, title, message)
    }

    // PRIVATE METHODS.
    private fun populateView(@DrawableRes icon: Int, title: String?, message: String?) {
        ivIcon.setImageResource(icon)

        if (!TextUtils.isEmpty(title)) {
            tvTitle.text = title
            tvTitle.visibility = View.VISIBLE
        } else {
            tvTitle.visibility = View.GONE
        }

        if (!TextUtils.isEmpty(message)) {
            tvMessage.text = message
            tvMessage.visibility = View.VISIBLE
        } else {
            tvMessage.visibility = View.GONE
        }
    }

}