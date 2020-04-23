/* Author:  Michael Ressler
 * Program: Control Application
 * Class:   EGEN 310
*/

package com.example.controlapp310;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //variables for buttons, switch and Bluetooth objects
    Button forward_btn, left_btn, right_btn, forward_left_btn, forward_right_btn, reverse_btn, reverse_left_btn, reverse_right_btn, connect_btn, disconnect_btn;
    Switch boost_switch;
    String address = null;
    String err = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket Socket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    String command; //string variable that will store value to be transmitted to the bluetooth module

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        address = "98:D3:11:FC:48:82"; //receive the MAC address of the bluetooth device

        setContentView(R.layout.activity_main);

        //outputStream = new ByteArrayOutputStream(1024);  // <- needed possibly

        //declaration of button variables
        forward_btn = (Button) findViewById(R.id.forward_btn);
        forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
        reverse_btn = (Button) findViewById(R.id.reverse_btn);
        boost_switch = (Switch) findViewById(R.id.boost_switch);
        connect_btn = (Button) findViewById(R.id.bluetooth_connect_btn);
        disconnect_btn = (Button) findViewById(R.id.bluetooth_disconnect_btn);

        //OnTouchListener code for the forward button (button long press)
        forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) //MotionEvent.ACTION_DOWN is when you hold a button down
                {
                    command = "1";
                    System.out.println("forward!\n" + command.getBytes());

                    try
                    {
                        Socket.getOutputStream().write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e)
                    {
                        msg("error");
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP) //MotionEvent.ACTION_UP is when you release a button
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

        //OnTouchListener code for the forward left button (button long press)
        forward_left_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "3";

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

        //OnTouchListener code for the reverse left button (button long press)
        reverse_left_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "4";

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

        //OnTouchListener code for the forward right button (button long press)
        forward_right_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "5";

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

        //OnTouchListener code for the reverse right button (button long press)
        reverse_right_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "6";

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

        //OnTouchListener code for the right button (button long press)
        right_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "7";

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

        //OnTouchListener code for the left button (button long press)
        left_btn.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    command = "8";

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

        //switch button that adjusts the vehicle's speed
        boost_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    // The toggle is enabled
                    command = "9";

                    try
                    {
                        Socket.getOutputStream().write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        msg("error");
                    }
                } else {
                    // The toggle is disabled
                    command = "10";

                    try
                    {
                        Socket.getOutputStream().write(command.getBytes());
                    }
                    catch (IOException e)
                    {
                        msg("error");
                    }
                }
            }
        });

        //OnClickListener code for the disconnect bluetooth button (button press)
        disconnect_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                try {
                    Socket.close();
                } catch (IOException e) {
                    msg("error");
                }

            }
        });

        //OnClickListener code for connect button that connects device to Bluetooth module
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect_btn.setOnClickListener(this);
                if(Socket == null)
                {
                    new ConnectBT().execute(); //calls class to connect Bluetooth
                }

            }
        });

    }


    private void msg(String err)
    {
        Toast.makeText(getApplicationContext(),err,Toast.LENGTH_LONG).show();
    }

    //ConnectBT class that establishes BT connection and checks for success/failure
    private class ConnectBT extends AsyncTask<Void, Void, Void>
    {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MainActivity.this, "Connecting...", "Seriously wait!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (Socket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    Socket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    Socket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//Exception block if connection failed
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //confirms connection success/failure
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }




    @Override
    protected void onStart()
    {
        super.onStart();
    }

}
