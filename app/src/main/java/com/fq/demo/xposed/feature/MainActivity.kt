package com.fq.demo.xposed.feature

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fq.demo.xposed.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity2019/3/12 3:23 PM
 * @desc :
 *
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_tv_label.text = "Hello init"

        m_tv_models.text = Build.MODEL
        m_tv_version.text = Build.VERSION.RELEASE
    }

    fun getTvLabel() = m_tv_label


}