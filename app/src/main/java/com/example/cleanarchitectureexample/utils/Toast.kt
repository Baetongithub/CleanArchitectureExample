package com.example.cleanarchitectureexample.utils

import android.content.Context
import android.widget.Toast

object Toast {

    fun show(context: Context?, txt: String) =
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show()
}