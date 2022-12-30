package com.example.a3tracker.ui.tasks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.a3tracker.DataClasses.Tasks
import com.example.a3tracker.Enums.TaskPriority
import com.example.a3tracker.Enums.TaskStatus
import com.example.a3tracker.R
import com.example.a3tracker.ViewModels.CurrentUserViewModel
import com.example.a3tracker.ViewModels.UsersViewModel
import com.example.a3tracker.ui.groups.GroupsViewModel
import java.text.SimpleDateFormat

class TasksAdapter(private val tasksList:MutableList<Tasks>,private val allUsersVM : UsersViewModel,private val groupsVM: GroupsViewModel) :RecyclerView.Adapter<TasksAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = tasksList[position]
        val dateformat2 = SimpleDateFormat("MMMM dd yyyy")
        holder.taskTitle.text = currentItem.title
        holder.groupName.text = groupsVM.getGroupById(currentItem.groupId) + " project"
        holder.assignedTime.text = currentItem.createdAt.toString().substring(11,16)
        holder.assigneeName.text = allUsersVM.getName(currentItem.assignee)
        holder.assignedBy.text = allUsersVM.getName(currentItem.createdBy)
        holder.deadlineDate.text = dateformat2.format(currentItem.deadline)
        when (currentItem.status){
            TaskStatus.NEW->{
                holder.newTaskStatus.visibility = View.VISIBLE
                holder.newTaskStatusText.visibility=View.VISIBLE
            }
            TaskStatus.BLOCKED->{
                holder.newTaskStatus.visibility=View.GONE
                holder.newTaskStatusText.visibility=View.GONE
            }
            TaskStatus.DONE->{
                holder.newTaskStatus.visibility=View.GONE
                holder.newTaskStatusText.visibility=View.GONE
            }
            TaskStatus.IN_PROGRESS->{
                holder.newTaskStatus.visibility=View.GONE
                holder.newTaskStatusText.visibility=View.GONE
            }
        }
        when(currentItem.priority){
            TaskPriority.LOW->{
                holder.highPrio.visibility=View.GONE
                holder.priorityText.text = "Low prio"
            }
            TaskPriority.MEDIUM->{
                holder.highPrio.visibility=View.GONE
                holder.priorityText.text= "Medium prio"
            }
            TaskPriority.HIGH->{
                holder.highPrio.visibility=View.VISIBLE
                holder.priorityText.text= "High prio"
            }
        }
        var tempdesc = currentItem.description
        if (currentItem.description.length>100){
             tempdesc = tempdesc.substring(0,100)
        }
        holder.description.text = tempdesc
        holder.progressBar.progress = currentItem.progress
        holder.percentDone.text = "${currentItem.progress}% Done"
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val taskTitle : TextView = itemView.findViewById(R.id.taskTitle)
        val groupName : TextView = itemView.findViewById(R.id.groupName)
        val assignedBy: TextView = itemView.findViewById(R.id.assignedBy)
        val assignedTime : TextView = itemView.findViewById(R.id.assignedTime)
        val newTaskStatus : ImageView = itemView.findViewById(R.id.newTaskStatus)
        val newTaskStatusText : TextView = itemView.findViewById(R.id.newTaskStatusText)
        val assigneeName : TextView = itemView.findViewById(R.id.assigneeName)
        val deadlineDate : TextView = itemView.findViewById(R.id.deadlineDate)
        val priorityText : TextView = itemView.findViewById(R.id.priorityText)
        val highPrio : ImageView = itemView.findViewById(R.id.highPrioIcon)
        val progressBar : ProgressBar = itemView.findViewById(R.id.progressBar)
        val percentDone : TextView = itemView.findViewById(R.id.percentDoneText)
        val description : TextView = itemView.findViewById(R.id.descriptionText)
    }

}