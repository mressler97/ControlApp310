package com.example.controlapp310;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //private final String DEVICE_ADDRESS = "98:D3:11:FC:48:82"; //MAC Address of Bluetooth Module

    Button forward_btn, forward_left_btn, forward_right_btn, reverse_btn, reverse_left_btn, reverse_right_btn, bluetooth_connect_btn, disconnect;
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

        //Intent newint = getIntent();
        address = "98:D3:11:FC:48:82"; //receive the address of the bluetooth device

        setContentView(R.layout.activity_main);

        //outputStream = new ByteArrayOutputStream(1024);  // <- needed possibly

        //declaration of button variables
        forward_btn = (Button) findViewById(R.id.forward_btn);
        forward_left_btn = (Button) findViewById(R.id.forward_left_btn);
        forward_right_btn = (Button) findViewById(R.id.forward_right_btn);
        reverse_btn = (Button) findViewById(R.id.reverse_btn);
        bluetooth_connect_btn = (Button) findViewById(R.id.bluetooth_connect_btn);
        disconnect = (Button) findViewById(R.id.bluetooth_disconnect_btn);

                                //calls class to connect

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
                    command = "10";
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
                    command = "10";
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
                    command = "10";
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
                    command = "10";
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

        disconnect.setOnClickListener(new View.OnClickListener(){
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

        //Button that connects the device to the bluetooth module when pressed
        bluetooth_connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bluetooth_connect_btn.setOnClickListener(this);
                if(Socket == null)
                {
                    new ConnectBT().execute();
                }

            }
        });

    }


    private void msg(String err)
    {
        Toast.makeText(getApplicationContext(),err,Toast.LENGTH_LONG).show();
    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(MainActivity.this, "Connecting...", "Please wait!");  //show a progress dialog
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
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
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
