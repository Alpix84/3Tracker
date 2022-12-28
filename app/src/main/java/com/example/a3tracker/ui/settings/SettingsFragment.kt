package com.example.a3tracker.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.a3tracker.Activities.AuthActivity
import com.example.a3tracker.R
import com.example.a3tracker.ViewModels.CurrentUserViewModel
import com.example.a3tracker.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private lateinit var textName : TextView
    private lateinit var profilePicture : ImageView
    private lateinit var logOut : Button
    private lateinit var emailText : TextView
    private lateinit var phonenumText : TextView
    private lateinit var locationText : TextView
    private lateinit var mentoredBy : TextView
    private val currentUserVM : CurrentUserViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewItems()
        registerListeners()
    }

    private fun registerListeners() {
        logOut.setOnClickListener{
            val prefs = requireActivity().getSharedPreferences("TRACKER", Context.MODE_PRIVATE)
            prefs.edit().clear().apply()
            startActivity(Intent(activity,AuthActivity::class.java))
        }
    }

    private fun initViewItems() {
        textName = requireView().findViewById(R.id.textName)
        textName.text = currentUserVM.getName()
        profilePicture = _binding!!.profilePicture
        Glide.with(activity).load(currentUserVM.getImageUrl()).into(profilePicture)
        logOut =_binding!!.logOutButton
        emailText = _binding!!.textEmail
        emailText.text = currentUserVM.getEmail()
        phonenumText = _binding!!.textPhoneNumber
        phonenumText.text = currentUserVM.getPhoneNumber()
        locationText = _binding!!.textLocation
        locationText.text = "Office Location: ${currentUserVM.getLocation()}"
        mentoredBy = _binding!!.textUsersMentor
        mentoredBy.text = "${currentUserVM.getName()}'s mentor"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}