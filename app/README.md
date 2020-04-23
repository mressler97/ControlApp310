# Source Code Editing
The following files can be modified/edited to change application design/functionalities.

## App Functions (MainActivity)
The [MainAcivity.java](/app/src/main/java/com/example/controlapp310/MainActivity.java/) is where the source code that can be edited to modify the application features and functions.  
https://github.com/mressler97/ControlApp310/blob/3fa9a027dc47a64c49674625a45fb766beaf7496/app/src/main/java/com/example/controlapp310/MainActivity.java#L60-L95

## App Layout
[activity_main.xml](/app/src/main/res/layout/activity_main.xml/) file is the layout for the app. Buttons can be modified/adjusted here or more can be added. 

## App Orientation
In [AndroidManifest.xml](/app/src/main/AndroidManifest.xml/), you can modify `"fullSensor"` in the code below, 

```html
<activity android:name=".MainActivity" android:screenOrientation="fullSensor">
        <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
</activity>
  ```
  to `"landscape"` or `"portrait"` to lock screen orientation respectively. `"fullSensor"` auto-detects the device's orientation which is currently set.
