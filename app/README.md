# Source Code Editing
The following files can be modified/edited to change application design/functionalities.

## App Functions (MainActivity)
The [MainAcivity.java](/app/src/main/java/com/example/controlapp310/MainActivity.java/) is where the source code that can be edited to modify the application features and functions.  
```java
        //OnTouchListener code for the reverse button (button long press)
        reverse_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "2";


                    try
                    {
                        Socket.getOutputStream().write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        msg("error");
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    command = "50";
                    try
                    {
                        Socket.getOutputStream().write(command.getBytes());
                    }
                    catch(IOException e)
                    {
                        msg("error");
                    }


                }
                return false;
            }
        });
```
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
