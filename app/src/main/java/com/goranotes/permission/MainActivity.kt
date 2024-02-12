package com.goranotes.permission

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.goranotes.gorapermission.Permission

class MainActivity: AppCompatActivity() {

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val resultPermission = Permission.handlePermissionsResult(permissions, grantResults, this)
        if(resultPermission.permissionGranted) {
            when (resultPermission.permissionType) {
               Permission.PermissionType.READ_CONTACTS -> {
                    try {
                        Toast.makeText(this, "Permission Read Contact is Granted", Toast.LENGTH_SHORT).show()
                    }catch (e: Exception){
                        Toast.makeText(this, e.printStackTrace().toString(), Toast.LENGTH_LONG).show()
                    }
                }

                else -> {}
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Permission.PermissionGrant(this, Permission.PermissionType.READ_CONTACTS)){
            Toast.makeText(this, "Permission Read Contact is Granted", Toast.LENGTH_SHORT).show()
        }else{
            Permission.ReqPermission(this, Permission.PermissionType.READ_CONTACTS)
        }
    }
}