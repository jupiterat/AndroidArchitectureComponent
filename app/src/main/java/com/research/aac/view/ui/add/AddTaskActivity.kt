package com.research.aac.view.ui.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.research.aac.R
import com.research.aac.databinding.AddTaskActivityBinding
import com.research.aac.viewmodel.AddTaskViewModel

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: AddTaskActivityBinding

    private lateinit var viewModel: AddTaskViewModel

    companion object {
        const val ADD_TASK_REQUEST_CODE = 12121
        const val ADD_TASK_DATA_KEY = "ADD_TASK_DATA_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_activity)
        binding = DataBindingUtil.setContentView(this, R.layout.add_task_activity)
        binding.lifecycleOwner = this


        binding.addTask.setOnClickListener {
            val data = Intent()
            data.putExtra(ADD_TASK_DATA_KEY, viewModel.task)
            setResult(Activity.RESULT_OK, data)
            finish()
        }

        viewModel = ViewModelProvider(this).get(AddTaskViewModel::class.java)
        binding.viewModel = viewModel
    }
}