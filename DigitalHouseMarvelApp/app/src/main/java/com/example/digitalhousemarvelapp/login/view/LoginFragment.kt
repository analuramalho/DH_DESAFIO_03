package com.example.digitalhousemarvelapp.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.digitalhousemarvelapp.R
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {
    private lateinit var _view:View
    private var _navController: NavController? = null

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

        _navController=findNavController()

        val btnLogin = _view.findViewById<Button>(R.id.btnLogin_Login)
        btnLogin.setOnClickListener {
            redirectHome()
        }

        val btnCreateAccount = _view.findViewById<Button>(R.id.btnCreateAccount_Login)
        btnCreateAccount.setOnClickListener {
            redirectRegister()
        }


    }


    private fun validateFields(): Boolean {
        val email = view?.findViewById<TextInputLayout>(R.id.textEmail_Login)
        email?.error = null
        val emailText = email?.editText?.text.toString()

        val senha = view?.findViewById<TextInputLayout>(R.id.textPassword_Login)
        senha?.error = null
        val senhaText = senha?.editText?.text.toString()

        if (emailText.trim() == "") {
            email?.error = getString(R.string.erro_email)
            return false
        }

        if (senhaText.trim() == "") {
            senha?.error = getString(R.string.erro_senha)
            return false
        }

        return true
    }


    private fun redirectHome() {
       if(validateFields()) _navController!!.navigate(R.id.action_loginFragment_to_comicListFragment)
    }

    private fun redirectRegister() {
        _navController!!.navigate(R.id.action_loginFragment_to_registerFragment)
    }
}