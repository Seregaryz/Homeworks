package com.example.homeworks


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_authorization.*
import java.lang.Thread.sleep

class AuthorizationActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_authorization)
        et_sign_in_pass.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                ti_sign_in_pass.error = null
                ti_sign_in_pass.isErrorEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

    }

    fun onLoginButtonClick(view : View){
        progressBar.visibility = View.VISIBLE
        Thread(Runnable {
            sleep(5000)
            runOnUiThread{
                progressBar.visibility = View.INVISIBLE
                val pass = et_sign_in_pass.text.toString()
                if (pass == PasswordRepository.password) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else setPasswordError()
            }
        }).start()
    }

    fun onChangePassClick(view: View){
        val intent = Intent(this, ChangePassActivity::class.java)
        startActivity(intent)
    }

    private fun setPasswordError() {
        ti_sign_in_pass.error = getString(R.string.validate_password)
    }

    object PasswordRepository {
        var password: String = "123456"
    }


}
