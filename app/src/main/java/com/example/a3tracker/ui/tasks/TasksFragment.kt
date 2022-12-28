package com.example.a3tracker.ui.tasks

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.a3tracker.ViewModels.CurrentUserViewModel
import com.example.a3tracker.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private val currentUserVM : CurrentUserViewModel by activityViewModels()
    private var _binding: FragmentTasksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[TasksViewModel::class.java]

        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val sharedPreferences = requireActivity().getSharedPreferences("TRACKER", Context.MODE_PRIVATE)
        val retrievedToken = sharedPreferences.getString("token",null)
        val retrievedDeadline = sharedPreferences.getLong("deadline",12345678)
        currentUserVM.updateLoginResponse(retrievedDeadline,retrievedToken.toString(),0)
        currentUserVM.getCurrentUser()
        Log.i("TASKS FRAGMENT",currentUserVM.getName())
        val textView: TextView = binding.textTasks
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}