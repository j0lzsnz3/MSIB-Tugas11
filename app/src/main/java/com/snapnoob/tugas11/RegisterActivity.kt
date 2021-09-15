package com.snapnoob.tugas11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.snapnoob.tugas11.databinding.ActivityRegisterBinding
import com.snapnoob.tugas11.preference.RegisteredUserPreference
import com.snapnoob.tugas11.preference.RegisteredUserPreferenceImpl

/**
 * Halaman register digunakan untuk mendaftar,
 * yang nantinya data akan disimpan di SharedPreference,
 * untuk digunakan untuk Login
 */
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var view: View

    private lateinit var registeredUserPreference: RegisteredUserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        registeredUserPreference = RegisteredUserPreferenceImpl(this)

        initView()
    }

    private fun initView() {
        binding.btnRegister.setOnClickListener {
            val editTextViews = listOf<EditText>(
                binding.edtName,
                binding.edtEmailAddress,
                binding.edtPassword,
                binding.edtPasswordConfirm,
                binding.edtBloodType
            )

            val allInputIsValid = AppUtil.checkInputEditText(editTextViews)
            if (allInputIsValid) {
                val password = binding.edtPassword.text.toString()
                val passwordConfirm = binding.edtPasswordConfirm.text.toString()

                if (password == passwordConfirm) {
                    saveToSharedPreference()
                    AppUtil.showToastOnTop(this, getString(R.string.msg_register_success))
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    AppUtil.showToastOnTop(this, getString(R.string.msg_password_not_match), true)
                }
            } else {
                AppUtil.showToastOnTop(this, getString(R.string.msg_input_not_valid), true)
            }
        }
    }

    // menyimpan data ke Shared Preference
    private fun saveToSharedPreference() {
        val name = binding.edtName.text.toString()
        val email = binding.edtEmailAddress.text.toString()
        val password = binding.edtPassword.text.toString()
        val bloodType = binding.edtBloodType.text.toString()

        registeredUserPreference.setUserName(name)
        registeredUserPreference.setUserEmail(email)
        registeredUserPreference.setUserPassword(password)
        registeredUserPreference.setUserBloodType(bloodType)
    }

}