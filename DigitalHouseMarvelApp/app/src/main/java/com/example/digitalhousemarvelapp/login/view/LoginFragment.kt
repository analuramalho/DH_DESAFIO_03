package com.example.digitalhousemarvelapp.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.digitalhousemarvelapp.R

class LoginFragment : Fragment() {
    private lateinit var _view:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _view=view

        val btnLogin = _view.findViewById<Button>(R.id.btnLogin_Login)
        btnLogin.setOnClickListener {
            _view.findNavController().navigate(R.id.action_loginFragment_to_comicListFragment)
        }

        val btnCreateAccount = _view.findViewById<Button>(R.id.btnCreateAccount_Login)
        btnCreateAccount.setOnClickListener {
            _view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


    }

}