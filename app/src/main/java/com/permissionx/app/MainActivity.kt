package com.permissionx.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.app.databinding.ActivityMainBinding
import com.permissionx.ouodev.PermissionX

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.makeCallBtn.setOnClickListener {
            PermissionX.request(
                this,
                Manifest.permission.CALL_PHONE
            ) { allGranted, deniedList ->
                if (allGranted) {
                    call()
                } else {
                    Toast.makeText(
                        this, "You denied $deniedList",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            /**
             * 一次性申请多个权限
             */
/*            PermissionX.request(
                this,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS
            ) { allGranted, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "All permissions are granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}