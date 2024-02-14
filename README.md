# Gora Permission [![](https://jitpack.io/v/goranotes/gora-permission.svg)](https://jitpack.io/#goranotes/gora-permission)
This project aims to provide an easy-to-use tool for checking and requesting permissions in Android applications. Gora Permission helps developers manage application permissions more effectively, ensuring that applications only use the necessary permissions.

<br/>

## Features

- Checking the status of application permissions.
- Requesting permissions if not yet granted.

<br/>

## Installation

**Step 1.** Add the JitPack repository to your build file

**Gradle Setup**

```Kotlin
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Maven Setup**

```Xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

<br/>

**Step 2.** Add the dependency

**Gradle Setup**

```Kotlin
dependencies {
        implementation 'com.github.goranotes:gora-permission:0.0.2'
}
```

**Maven Setup**

```Xml
<dependency>
    <groupId>com.github.goranotes</groupId>
    <artifactId>gora-permission</artifactId>
    <version>0.0.2</version>
</dependency>
```

<br/>

## Usage

Declare app permissions in `AndroidManifest.xml`, for example the permission to read contacts. 

```Xml
<manifest ...>
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <application ...>
        ...
    </application>
</manifest>
```
<br/>
Check and Request Permission

```Kotlin
//Check Permission
if(Permission.PermissionGrant(this, Permission.PermissionType.READ_CONTACTS)){
    //Do something
    ...
}else{
    //Request Permission
    Permission.ReqPermission(this, Permission.PermissionType.READ_CONTACTS)
}
```
<br/>
Currently the permission types available are as follows

| PermissionType                               | Functionality                                                                 |
|----------------------------------------------|------------------------------------------------------------------------------|
| READ_CONTACTS    | Allows reading contacts stored on the user's device.                         |
| CALL_PHONE       | Allows initiating phone calls without requiring additional user interaction.|
| WRITE_EXTERNAL_STORAGE | Allows writing data to the external storage of the device.               |
| READ_EXTERNAL_STORAGE  | Allows reading data from the external storage of the device.              |
| ACCESS_FINE_LOCATION | Allows accessing the user's location with high accuracy, typically using GPS.|
| ACCESS_MEDIA_LOCATION | Allows accessing media location information, such as the location of photos or videos stored on the device.|
| ACCESS_FULL_LOCATION | Allows accessing general location information, including low-accuracy location.|

<br/>

## Result Request Permissions

get the result of the permission request in the onRequestPermissionsResult override function
```Kotlin
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
                //Do something after permission is granted
                    ...
            }

            else -> {}
        }
    }
}
```

<br/>


![Result Request](https://miro.medium.com/v2/resize:fit:648/format:webp/1*G2Zt3cNuq9v5bIQs3_Duag.png)

<br/>

## Contribution

We are very open to contributions. If you would like to contribute to this project, please open an Issue to find tasks that can be worked on or submit a Pull Request with fixes or new features.

<br/>

## License

This project is licensed under the [MIT](https://github.com/goranotes/gora-permission/blob/master/LICENSE) License - see the LICENSE file for more details.
