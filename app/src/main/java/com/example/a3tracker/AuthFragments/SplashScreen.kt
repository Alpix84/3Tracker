package com.example.a3tracker.AuthFragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.a3tracker.Activities.MainActivity
import com.example.a3tracker.ViewModels.CurrentUserViewModel
import com.example.a3tracker.Interfaces.ApiInterface
import com.example.a3tracker.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://tracker-3track.a2hosted.com/"


class SplashScreen : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initViewItems()
        //registerListeners()
        findNavController().navigate(R.id.action_splashScreen_to_loginScreen)
    }

    private fun initViewItems() {

    }

    private fun registerListeners() {

    }
}