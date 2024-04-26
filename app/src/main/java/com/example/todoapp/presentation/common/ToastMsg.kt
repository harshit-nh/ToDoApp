package com.example.todoapp.presentation.common

import android.content.Context
import android.widget.Toast

fun ToastMsg(
    context: Context,
    msg:String
){
    Toast.makeText(
        context,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}