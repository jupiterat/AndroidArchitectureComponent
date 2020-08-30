package com.research.aac.view.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.research.aac.R
import com.research.aac.databinding.MainFragmentBinding
import com.research.aac.view.model.Task
import com.research.aac.view.ui.add.AddTaskActivity
import com.research.aac.viewmodel.TaskListViewModel
import kotlinx.android.synthetic.main.task_item.view.*
import org.threeten.bp.LocalDate

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: TaskListViewModel
    private lateinit var adapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        adapter = TaskListAdapter(requireContext())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            this.layoutManager = layoutManager

        }
        binding.recyclerview.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(
            TaskListViewModel::class.java
        )

        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            if (tasks != null) {
                adapter.setTasks(tasks)
            }
        })
        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val task = data?.getSerializableExtra(AddTaskActivity.ADD_TASK_DATA_KEY)
        if (task != null) {
            val data = (task as Task)
            viewModel.addTask(data)
        }
    }

    class TaskListAdapter internal constructor(
        context: Context
    ) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)
        private var tasks: List<com.research.aac.infra.entity.Task>? = null

        inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun setTaskTitle(title: String) {
                itemView.title.text = "title: $title"
            }

            fun setTaskDeadline(deadline: LocalDate) {
                itemView.deadline.text = "time: $deadline"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            val itemView = inflater.inflate(R.layout.task_item, parent, false)
            return TaskViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            val current = tasks!![position]
            holder.setTaskTitle(current.title)
            holder.setTaskDeadline(current.deadline)
        }

        internal fun setTasks(tasks: List<com.research.aac.infra.entity.Task>) {
            this.tasks = tasks
            notifyDataSetChanged()
        }

        override fun getItemCount() = tasks?.size ?: 0
    }
}