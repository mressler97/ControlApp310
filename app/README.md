# Source Code Editing
The following files can be modified/edited to change application design/functionalities.

## App Functions
The [MainAcivity.java](/app/src/main/java/com/example/controlapp310/MainActivity.java/) is where the source code that can be edited to modify the application features and functions.  

Within the onCreate function
```java
    protected void onCreate(Bundle savedInstanceState)
    {
        ...
    }
```
button functions can be added here. An example is the `reverse_button` where a long press will send a value to the outputStream. Once the button is released, a different value is sent over telling the motors to stop. 
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
## Arduino Code
I have also included the [Arduino_code](/arduino_code/arduino_code/arduino_code.ino/) our group used created by myself and Andy Maehl.
Here motor speeds, servo adjustments, pins etc. can be modifed. The movement functions share the same name with the control application functions, respectively. Therefore, any function in the app can be found easily in this code for adjustments.

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
