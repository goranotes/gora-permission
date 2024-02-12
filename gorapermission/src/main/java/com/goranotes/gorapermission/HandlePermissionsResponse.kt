package com.goranotes.gorapermission

data class HandlePermissionsResponse(
    var permissionType: Permission.PermissionType?     = null,
    var permissionGranted: Boolean  = false
)