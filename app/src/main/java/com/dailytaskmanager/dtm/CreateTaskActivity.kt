package com.dailytaskmanager.dtm

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.dailytaskmanager.dtm.databinding.ActivityCreateTaskBinding

class CreateTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var reminderDialog: Dialog
    private var reminder_before_10 = false
    private var reminder_finished_10 = false
    private var alarm_start = false
    private var alarm_finished = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        val actionbar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#17EBDE"))
        actionbar?.setBackgroundDrawable(colorDrawable)
        setContentView(binding.root)
        reminderDialog = Dialog(this)
        binding.btnAlramandreminderId.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.alermturnondia, null, false)
            reminderDialog.setContentView(view)
            reminderDialog.show()
            reminderDialog.setCancelable(false)
            val cancel: Button = view.findViewById(R.id.btn_alarm_Cancel_Dial_id)
            cancel.setOnClickListener {
                reminderDialog.dismiss()
            }
            val done: Button = view.findViewById(R.id.btn_alarm_Submit_Dial_id)
            val checkBox1: CheckBox = view.findViewById(R.id.cbx_alarm1_dial_id)
            val checkBox2: CheckBox = view.findViewById(R.id.cbx_alarm2_dial_id)
            val checkBox3: CheckBox = view.findViewById(R.id.cbx_alarm3_dial_id)
            val checkBox4: CheckBox = view.findViewById(R.id.cbx_alarm4_dial_id)
            if (reminder_before_10) {
                checkBox1.isChecked = true
            }
            if (alarm_start) {
                checkBox2.isChecked = true
            }
            if (reminder_finished_10) {
                checkBox3.isChecked = true
            }
            if (alarm_finished) {
                checkBox4.isChecked = true
            }
            checkBox1.setOnCheckedChangeListener { _, _ ->
                reminder_before_10 = !reminder_before_10
            }
            checkBox2.setOnCheckedChangeListener { compoundButton, b ->
                alarm_start = !alarm_start
            }

            checkBox3.setOnCheckedChangeListener { compoundButton, b ->
                reminder_finished_10 = !reminder_finished_10
            }
            checkBox4.setOnCheckedChangeListener { compoundButton, b ->
                alarm_finished = !alarm_finished
            }

        }
    }
}