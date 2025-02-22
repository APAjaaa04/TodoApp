package com.example.todoapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.TodoItemLayoutBinding
import com.example.todoapp.model.Todo

class TodoListAdapter (val todoList:ArrayList<Todo>,val adapterOnClick : (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>() {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int)
    {
        holder.binding.checkBox.text = todoList[position].title
        holder.binding.checkBox.setOnCheckedChangeListener {
                compoundButton, b ->
            if(b) {
                adapterOnClick(todoList[position])
            }
        }

        holder.binding.imgEdit.setOnClickListener {
            val action =
                TodoListFragmentDirections.actionTodoListFragmentToEditTodoFragment(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

}
