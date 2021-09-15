package com.snapnoob.tugas11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.snapnoob.tugas11.databinding.ActivityLoginBinding
import com.snapnoob.tugas11.preference.LoginPreference
import com.snapnoob.tugas11.preference.LoginPreferenceImpl
import com.snapnoob.tugas11.preference.RegisteredUserPreference
import com.snapnoob.tugas11.preference.RegisteredUserPreferenceImpl

/**
 * Halaman login digunakan untuk melakukan login,
 * dengan membandingkan data yang sudah disimpan di SharedPreference,
 * dengan input dari pengguna.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var view: View

    private lateinit var loginPreference: LoginPreference
    private lateinit var registeredUserPreference: RegisteredUserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        loginPreference = LoginPreferenceImpl(this)
        registeredUserPreference = RegisteredUserPreferenceImpl(this)

        initView()
    }

    private fun initView() {
        binding.btnLogin.setOnClickListener {
            val views = listOf<EditText>(
                binding.edtEmailAddress,
                binding.edtPassword
            )
            val allInputIsValid = AppUtil.checkInputEditText(views)
            if (allInputIsValid) {
                validateCredentials()
            } else {
                AppUtil.showToastOnTop(this, getString(R.string.msg_input_not_valid), true)
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    // validasi email dan password
    private fun validateCredentials() {
        val email = binding.edtEmailAddress.text.toString()
        val password = binding.edtPassword.text.toString()

        val savedEmail = registeredUserPreference.getUserEmail()
        val savedPassword = registeredUserPreference.getUserPassword()
        if (savedEmail == email && savedPassword == password) {
            AppUtil.showToastOnTop(this, getString(R.string.msg_login_success))

            // reset email dan password
            binding.edtEmailAddress.setText("")
            binding.edtPassword.setText("")
        } else {
            AppUtil.showToastOnTop(this, getString(R.string.msg_login_failed), true)
        }
    }
}