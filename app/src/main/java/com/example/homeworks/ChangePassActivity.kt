package com.example.homeworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_change_pass.*

class ChangePassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)
    }

    fun onChangePassClick(view: View){
        val newPass = et_change_pass.text.toString()
        if(!newPass.isEmpty()) {
            AuthorizationActivity.PasswordRepository.password = newPass
        }
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }
}
