package com.dailytaskmanager.dtm

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dailytaskmanager.dtm.databinding.ActivityCreateTaskBinding
import com.dailytaskmanager.dtm.db.TaskManagerViewModel
import com.dailytaskmanager.dtm.db.TaskModel
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
    private var saveButtonClicked = false
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
    private var mtaskId = 0
    private lateinit var liveTaskManager: TaskManagerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        val actionbar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#17EBDE"))
        actionbar?.setBackgroundDrawable(colorDrawable)
        setContentView(binding.root)
        liveTaskManager = ViewModelProvider(this)[TaskManagerViewModel::class.java]
        reminderDialog = Dialog(this)
        mtaskId = intent.getIntExtra("taskId", 1)
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
                changeButtonColor(binding)
            }
            checkBox2.setOnCheckedChangeListener { compoundButton, b ->
                alarm_start = !alarm_start
                changeButtonColor(binding)
            }

            checkBox3.setOnCheckedChangeListener { compoundButton, b ->
                reminder_finished_10 = !reminder_finished_10
                changeButtonColor(binding)
            }
            checkBox4.setOnCheckedChangeListener { compoundButton, b ->
                alarm_finished = !alarm_finished
                changeButtonColor(binding)
            }
            done.setOnClickListener {
//                if (reminder_finished_10 || reminder_before_10 || alarm_finished || alarm_start) {
//                    binding.btnAlramandreminderId.setBackgroundColor(resources.getColor(R.color.bottomnavcolor))
//                } else {
//                    binding.btnAlramandreminderId.setBackgroundColor(resources.getColor(R.color.dimGray))
//                }
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
            var totalTime: Long = 0
            val taskTotalHour = binding.spinnerHourId.selectedItem.toString().toLong()
            val taskTotalMin = binding.spinnerMinuteId.selectedItem.toString().toLong()
            if (taskTotalMin > 0) {
                totalTime = (taskTotalMin * 60000) + (taskTotalHour * 3600000)
            }
            val title = binding.etCreateTaskTitleId.text.toString()
            val desc = binding.etCreateTaskDescId.text.toString()
            val startTime = binding.tvCreateTimeStartViewId.text.toString()
            val startDate = binding.tvCreateDateStartViewId.text.toString()
            val calendar = Calendar.getInstance()
            calendar.set(myYear, myMonth, myDay, myHour, minute)
            val taskStartTime = calendar.timeInMillis
            var taskStartReminderId: Int = mtaskId
            var taskStartAlarmId: Int = taskStartReminderId + 1
            var taskEndReminderId: Int = taskStartAlarmId + 1
            var taskEndAlarmId: Int = taskEndReminderId + 1
            if (validateFields(totalTime, title, desc, startDate, startTime)) {
                if (reminder_before_10) {
                    setAlarm(taskStartReminderId)
                }
                if (reminder_finished_10) {
                    setAlarm(taskEndReminderId)
                }
                if (alarm_start) {
                    setAlarm(taskStartAlarmId)
                }
                if (alarm_finished) {
                    setAlarm(taskEndAlarmId)
                }
                val addTask = TaskModel(
                    taskEndAlarmId + 1,
                    title,
                    desc,
                    taskStartTime,
                    totalTime,
                    taskStartReminderId,
                    taskStartAlarmId,
                    taskEndReminderId,
                    taskEndAlarmId,
                    reminder_before_10,
                    alarm_finished,
                    startDate,
                    false,
                    startTime
                )
                liveTaskManager.insertTask(addTask)
            } else {
                Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show()
            }
//            startActivity(Intent(this, MainActivity::class.java).apply {
//                finish()
//            })
        }
    }

    private fun validateFields(
        totalTime: Long,
        title: String,
        desc: String,
        startDate: String,
        startTime: String
    ): Boolean {
        return when {
            totalTime <= 0 -> false
            title == null -> false
            desc == null -> false
            startDate == "Not Set" -> false
            startTime == "Not Set" -> false
            else -> true
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

    override fun onTimeSet(time: TimePicker?, hour: Int, min: Int) {
        myHour = hour
        myMinute = min
        if (startTime) {
            binding.tvCreateTimeStartViewId.text = "$hour:$min"
        } else {
            binding.tvCreateTimeEntdiewId.text = "$hour:$min"

        }
    }

    private fun setAlarm(taskId: Int) {

    }
}