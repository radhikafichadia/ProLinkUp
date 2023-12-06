package com.businesstinder.prolinkup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private var mRegister: Button? = null
    private var mEmail: EditText? = null
    private var mPassword: EditText? = null
    private var mName: EditText? = null
    private var mRadioGroup: RadioGroup? = null
    private var mAuth: FirebaseAuth? = null
    private var firebaseAuthStateListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        mAuth = FirebaseAuth.getInstance()
        firebaseAuthStateListener = AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val intent = Intent(this@RegistrationActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                return@AuthStateListener
            }
        }
        mRegister = findViewById<View>(R.id.register) as Button
        mEmail = findViewById<View>(R.id.email) as EditText
        mPassword = findViewById<View>(R.id.password) as EditText
        mName = findViewById<View>(R.id.name) as EditText
        mRadioGroup = findViewById<View>(R.id.radioGroup) as RadioGroup
        mRegister!!.setOnClickListener(View.OnClickListener {
            val selectId = mRadioGroup!!.checkedRadioButtonId
            val radioButton = findViewById<View>(selectId) as RadioButton
            if (radioButton.text == null) {
                return@OnClickListener
            }
            val email = mEmail!!.text.toString()
            val password = mPassword!!.text.toString()
            val name = mName!!.text.toString()
            mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener(this@RegistrationActivity) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(this@RegistrationActivity, "sign up error", Toast.LENGTH_SHORT).show()
                } else {
                    val userId = mAuth?.getCurrentUser()!!.uid
                    val currentUserDb = FirebaseDatabase.getInstance().reference.child("Users").child(userId)
                    //val userInfo: HashMap<String, Any?> = HashMap()

                    //userInfo["name"] = name
                    //userInfo["sex"] = radioButton.text.toString()
                    //userInfo["profileImageUrl"] = "default"
                    val userInfo = UserInfo(name, radioButton.text.toString(), "default")
                    //println("Name: ${userInfo["name"]}")
                    //println("Sex: ${userInfo["sex"]}")
                    //rintln("Profile Image URL: ${userInfo["profileImageUrl"]}")
                    //println( "Info " + userInfo["name"] + userInfo["sex"] + userInfo["profileImageUrl"])
                    println( "Uid" + userId)
                    println("Ok" + name + radioButton.text.toString())
                    currentUserDb.setValue(userInfo)
                    //currentUserDb.updateChildren(userInfo)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(firebaseAuthStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        mAuth!!.removeAuthStateListener(firebaseAuthStateListener!!)
    }
}
