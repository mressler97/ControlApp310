# README for source code editing

Read this if you want to edit the source code. 
* [MainAcivity file](/app/src/main/java/com/example/controlapp310/MainActivity.java/)
* [activity_main.xml](/app/src/main/res/layout/activity_main.xml/)

In [AndroidManifest.xml](/app/src/main/AndroidManifest.xml/), you can modify `"fullSensor"` in the code below 

```html
<activity android:name=".MainActivity" android:screenOrientation="fullSensor">
        <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
</activity>
  ```
  to `"landscape"` or `"portrait"` to lock screen orientation respectively. `"fullSensor"` auto-detects the device's orientation which is currently set.
