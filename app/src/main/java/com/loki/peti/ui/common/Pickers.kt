package com.loki.peti.ui.common

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import java.util.*


fun datePickerLayout(
    context: Context,
    onDateChange: (String) -> Unit,
): DatePickerDialog {

    val mCalendar = Calendar.getInstance()
    val year = mCalendar.get(Calendar.YEAR)
    val month = mCalendar.get(Calendar.MONTH)
    val day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    return DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDay: Int ->
            onDateChange("$mDay/${mMonth + 1}/$mYear")
        }, year, month, day
    )
}

fun timePickerLayout(
    context: Context,
    onTimeChange: (String) -> Unit
): TimePickerDialog {

    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    return TimePickerDialog(
        context,
        {_, hour : Int, minute: Int ->
            onTimeChange("$hour:$minute")
        }, mHour, mMinute, true
    )
}