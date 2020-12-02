package com.example.digitalhousemarvelapp.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.digitalhousemarvelapp.R

class RegisterFragment : Fragment() {
    private lateinit var _view:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _view = view

        val btnSave = _view.findViewById<Button>(R.id.btnSave_Register)
        btnSave.setOnClickListener {
            _view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}