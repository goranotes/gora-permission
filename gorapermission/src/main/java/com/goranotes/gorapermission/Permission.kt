package com.goranotes.gorapermission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat

object Permission {

    enum class PermissionType{
        READ_CONTACTS,
        CALL_PHONE,
        WRITE_EXTERNAL_STORAGE,
        READ_EXTERNAL_STORAGE,
        ACCESS_FINE_LOCATION,
        ACCESS_MEDIA_LOCATION,
        ACCESS_FULL_LOCATION
    }

    fun PermissionGrant(ctx: Context, permissionType: PermissionType): Boolean{

        var isGranted = false
        when (permissionType) {
            PermissionType.READ_CONTACTS ->
                if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.READ_CONTACTS)== PackageManager.PERMISSION_GRANTED){
                    isGranted = true
                }
            PermissionType.CALL_PHONE ->
                if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
                    isGranted = true
                }
            PermissionType.WRITE_EXTERNAL_STORAGE ->
                if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    isGranted = true
                }
            PermissionType.READ_EXTERNAL_STORAGE ->
                if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    isGranted = true
                }
            PermissionType.ACCESS_FINE_LOCATION ->
                if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    isGranted = true
                }
            PermissionType.ACCESS_MEDIA_LOCATION ->
                if(ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_MEDIA_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    isGranted = true
                }
            PermissionType.ACCESS_FULL_LOCATION ->
                if(
                    ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(ctx, android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
                ){
                    isGranted = true
                }
        }
        return isGranted
    }

    fun ReqPermission(activity: Activity, permissionType: PermissionType, requestCode: Int = 0){

        when (permissionType) {
            PermissionType.READ_CONTACTS ->
                if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(arrayOf(android.Manifest.permission.READ_CONTACTS), requestCode)
                }
            PermissionType.CALL_PHONE ->
                if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), requestCode)
                }
            PermissionType.WRITE_EXTERNAL_STORAGE ->
                if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode)
                }
            PermissionType.READ_EXTERNAL_STORAGE ->
                if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), requestCode)
                }
            PermissionType.ACCESS_FINE_LOCATION ->
                if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
                }
            PermissionType.ACCESS_MEDIA_LOCATION ->
                if(ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_MEDIA_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                    activity.requestPermissions(arrayOf(android.Manifest.permission.ACCESS_MEDIA_LOCATION), requestCode)
                }
            PermissionType.ACCESS_FULL_LOCATION ->
                if(
                    ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                ) {
                    activity.requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), requestCode)

                }
        }
    }

    fun handlePermissionsResult(permissions: Array<out String>, grantResults: IntArray, activity : Activity) : HandlePermissionsResponse {

        val permissionResponse      = HandlePermissionsResponse()
        val type : PermissionType   = PermissionType.valueOf(permissions[0].substring(19))
        var isGranted               = false

        when (type) {
            PermissionType.READ_CONTACTS -> {

                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isGranted = true
                } else {
                    isGranted = false
                    Toast.makeText(activity, activity.getString(R.string.permission_contact_not_granted), Toast.LENGTH_SHORT).show()
                }
            }
            PermissionType.CALL_PHONE -> {

                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isGranted = true
                } else {
                    isGranted = false
                    Toast.makeText(activity, activity.getString(R.string.permission_phone_not_granted), Toast.LENGTH_SHORT).show()
                }
            }
            PermissionType.WRITE_EXTERNAL_STORAGE, PermissionType.READ_EXTERNAL_STORAGE -> {

                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isGranted = true
                } else {
                    isGranted = false
                    Toast.makeText(activity, activity.getString(R.string.permission_storage_not_granted), Toast.LENGTH_SHORT).show()
                }
            }
            PermissionType.ACCESS_FINE_LOCATION, PermissionType.ACCESS_MEDIA_LOCATION, PermissionType.ACCESS_FULL_LOCATION -> {

                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    isGranted = true
                } else {
                    isGranted = false
                    Toast.makeText(activity, activity.getString(R.string.permission_location_not_granted), Toast.LENGTH_SHORT).show()
                }
            }
        }

        permissionResponse.apply {
            permissionType    = type
            permissionGranted = isGranted
        }

        return permissionResponse
    }
}