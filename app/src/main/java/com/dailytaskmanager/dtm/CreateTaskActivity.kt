package com.dailytaskmanager.dtm

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.dailytaskmanager.dtm.databinding.ActivityCreateTaskBinding
import java.util.*

class CreateTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    private lateinit var binding: ActivityCreateTaskBinding
    private lateinit var reminderDialog: Dialog
    private var reminder_before_10 = false
    private var reminder_finished_10 = false
    private var alarm_start = false
    private var alarm_finished = false
    private var startTime = true

    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog

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
            done.setOnClickListener {
                if (reminder_finished_10 || reminder_before_10 || alarm_finished || alarm_start) {
                    binding.btnAlramandreminderId.setBackgroundColor(resources.getColor(R.color.bottomnavcolor))
                } else {
                    binding.btnAlramandreminderId.setBackgroundColor(resources.getColor(R.color.dimGray))
                }
                reminderDialog.dismiss()
            }
        }
        binding.btnCreateTaskStartDateId.setOnClickListener {
            startTime = true
            pickDateAndTime()
        }
        binding.btnCreateTaskEndDateId.setOnClickListener {
            pickDateAndTime()
            startTime = false
        }
        val hourAdapter = ArrayAdapter.createFromResource(
            this, R.array.hour, R.layout.spinneritem
        )
        val minuteAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.minute,
            R.layout.spinneritem
        )
        binding.spinnerHourId.adapter = hourAdapter
        binding.spinnerMinuteId.adapter = minuteAdapter
        binding.btnAlramanSaveId.setOnClickListener {

            val title = binding.etCreateTaskTitleId.text.toString()
            val desc = binding.etCreateTaskDescId.text.toString()
            val startTime = binding.tvCreateTimeStartViewId.text.toString()
            val startDate = binding.tvCreateDateStartViewId.text.toString()
            val totalTime = binding.tvCreateTimeEntdiewId.text.toString()
            startActivity(Intent(this, MainActivity::class.java).apply {
                finish()
            })
        }
    }

    private fun pickDateAndTime() {
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        day = calendar.get(Calendar.DAY_OF_MONTH)
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        timePickerDialog =
            TimePickerDialog(
                this@CreateTaskActivity, this@CreateTaskActivity, hour, minute,
                false
            )
        datePickerDialog = DatePickerDialog(this, this@CreateTaskActivity, year, month, day)
        datePickerDialog.show()
    }

    private fun changeButtonColor(binding: ActivityCreateTaskBinding) {
        if (reminder_finished_10 || reminder_before_10 || alarm_finished || alarm_start) {
            binding.btnAlramandreminderId.setBackgroundColor(resources.getColor(R.color.bottomnavcolor))
        } else {
            binding.btnAlramandreminderId.setBackgroundColor(resources.getColor(R.color.dimGray))
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month
        if (startTime) {
            binding.tvCreateDateStartViewId.text = "$myDay/$myMonth/$myYear"
        } else {
            binding.tvCreateDateEntdiewId.text = "$myDay/$myMonth/$myYear"
        }
        timePickerDialog.show()
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, min: Int) {
        if (startTime) {
            binding.tvCreateTimeStartViewId.text = "$hour:$min"
        } else {
            binding.tvCreateTimeEntdiewId.text = "$hour:$min"

        }
    }
}