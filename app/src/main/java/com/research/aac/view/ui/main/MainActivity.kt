package com.research.aac.view.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.research.aac.R
import com.research.aac.view.ui.add.AddTaskActivity
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        img_add_task.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, AddTaskActivity.ADD_TASK_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AddTaskActivity.ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (supportFragmentManager.findFragmentById(R.id.container) is MainFragment) {
                val fragment =
                    supportFragmentManager.findFragmentById(R.id.container) as MainFragment
                fragment.onActivityResult(requestCode, resultCode, data)
            }
        }
    }
}