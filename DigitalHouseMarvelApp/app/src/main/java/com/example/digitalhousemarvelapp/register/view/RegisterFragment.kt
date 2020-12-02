package com.example.digitalhousemarvelapp.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.digitalhousemarvelapp.R
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment() {
    private lateinit var _view:View
    private var _navController: NavController? = null
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

        _navController = Navigation.findNavController(view)

        val btnSave = _view.findViewById<Button>(R.id.btnSave_Register)
        btnSave.setOnClickListener {
           redirectLogin()
        }

        setBackNavigation()
    }



    private fun setBackNavigation() {
        _view.findViewById<ImageView>(R.id.imgBack_Register).setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }
    }

    private fun redirectLogin() {
        if(validateFieldsRegister())_navController!!.navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun validateFieldsRegister(): Boolean {

        val nome = _view?.findViewById<TextInputLayout>(R.id.textName_Register)
        nome?.error = null
        val nomeText = nome?.editText?.text.toString()

        val email = _view?.findViewById<TextInputLayout>(R.id.textEmail_Register)
        email?.error = null
        val emailText = email?.editText?.text.toString()

        val senha = _view?.findViewById<TextInputLayout>(R.id.textPassword_Register)
        senha?.error = null
        val senhaText = senha?.editText?.text.toString()

        if (nomeText.trim() == "") {
            nome?.error = getString(R.string.erro_nome)
            return false
        }

        if (emailText.trim() == "") {
            email?.error = getString(R.string.erro_email)
            return false
        }

        if (senhaText.trim() == "") {
            senha?.error = getString(R.string.erro_senha)
            return false
        }

        if (senhaText.trim().length < 6) {
            senha?.error = getString(R.string.minimo_senha)
            return false
        }
        return true
    }
}
