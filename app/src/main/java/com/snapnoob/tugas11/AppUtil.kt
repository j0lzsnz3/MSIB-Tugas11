package com.snapnoob.tugas11

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

object AppUtil {
    fun showToastOnTop(context: Context, message: String, isAnError: Boolean = false) {
        val toast = Toast(context)
        val layout = if (isAnError) R.layout.view_toast_top_red else R.layout.view_toast_top_green
        val view: View = LayoutInflater.from(context).inflate(layout, null)
        val toastMessage = view.findViewById<TextView>(R.id.tvToastMessage)
        toastMessage.text = message
        toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = view
        toast.show()
    }

    fun checkInputEditText(views: List<EditText>): Boolean {
        var isValid = true
        views.forEach {
            val values = it.text.toString()
            if (TextUtils.isEmpty(values)) {
                isValid = false
            }
        }
        return isValid
    }
}