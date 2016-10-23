package com.palarran.anchorboy;

/**
 * App name: AnchorBoy
 * Created by CB on 7/7/2016.
 *
 * App talks to an Arduino board that has a GPS and BlueTooth Shield installed, which is mounted in
 * a water proof buoy. This buoy is attached via line to an anchor.
 *
 * When App starts, it checks that Bluetooth is enabled. If not, it prompts user to enable it.
 *
 * Once Bluetooth is enabled it will show a list of previously paired devices if any are present.
 * After Bluetooth is enabled and paired App will monitor and get continuous GPS position updates
 * from Arduino, set the initial position. From there App will monitor position and if position
 * changes by a predefined or user defined distance, an alarm will sound on Android phone.
 *
 * App will then display amount of feet/yards/meters buoy has moved and the direction boat is
 * drifting and possibly speed in future builds.
 *
 * Future builds will also have ability to use google maps, send warning text messages...
 */

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class AnchorBoyMainActivity extends Activity {

    private TextView gpsPositionString;
    private Handler bluetoothIn;

    private final int handlerState = 0;        				 //used to identify handler message
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private final StringBuilder gpsData = new StringBuilder();

    // SPP(serial port profile) UUID service - this should work for most bluetooth devices
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor_boy_main);

        gpsPositionString = (TextView) findViewById(R.id.LAT_LONG);                     //Link textViews to respective views

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {                         // data coming from Bluetooth Buoy is handled here
                if (msg.what == handlerState) {										    // if message is what is expected
                    String readMessage = (String) msg.obj;                              // cast msg.obj to String
                    gpsData.append(readMessage);      						            // keep appending to string until end of string (!) is reached
                    int endOfLineIndex = gpsData.indexOf("!");                          // determine the end-of-string
                    if (endOfLineIndex > 0) {                                           // make sure there is data before "!"
                        String dataInPrint = gpsData.substring(0, endOfLineIndex);      // extract string
                        gpsPositionString.setText(dataInPrint);

                        if (gpsData.charAt(0) == 'F')								    // if message starts with "F" we know it is what we are looking for
                        {
                            String anchorSet = gpsData.toString();                      // get GPS value from string

                            gpsPositionString.setText(anchorSet);                       // update textview with GPS data
                        }
                        gpsData.delete(0, gpsData.length()); 			                // clear all string data
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter();       // get Bluetooth adapter
    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {

        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID); //creates secure outgoing connection with BT device using UUID
    }

    @Override
    public void onResume() {
        super.onResume();

        //Get MAC address from DeviceListActivity via intent
        Intent intent = getIntent();

        //Get the MAC address from the DeviceListActivity via EXTRA
        String address = intent.getStringExtra(PairBluetoothActivity.EXTRA_DEVICE_ADDRESS);

        //create device and set the MAC address
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try {
            btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
        }
        // Establish the Bluetooth socket connection.
        try
        {
            btSocket.connect();
        } catch (IOException e) {
            try
            {
                btSocket.close();
            } catch (IOException e2)
            {
                Toast.makeText(getBaseContext(), "Socket did not close", Toast.LENGTH_LONG).show();
            }
        }
        AnchorBoyMainActivity.ConnectedThread mConnectedThread = new AnchorBoyMainActivity.ConnectedThread(btSocket);
        mConnectedThread.start();

        //send a character when resuming. beginning transmission to check device is connected
        //If it is not an exception will be thrown in the write method and finish() will be called
        mConnectedThread.write();
    }

    @Override
    public void onPause() {
        super.onPause();
        try
        {
            btSocket.close();       //Close Bluetooth sockets when leaving activity
        } catch (IOException e2) {
            Toast.makeText(getBaseContext(), "Socket did not close", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickSetPosition(View view) {
        //TODO write code to handle what happens when the "Set Position" button is clicked.
        //TODO Hardcoding location for now. Need to change this for futureness

    }

    //create new class for connect thread
    private class ConnectedThread extends Thread {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        //creation of the connect thread
        ConnectedThread(BluetoothSocket socket) {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try {
                //Create I/O streams for connection
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Toast.makeText(getBaseContext(), "Socket creation failed", Toast.LENGTH_LONG).show();
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Keep looping to listen for received messages
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);        	//read bytes from input buffer
                    String readMessage = new String(buffer, 0, bytes);
                    // Send the obtained bytes to the UI Activity via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
        //write method
        void write() {
            byte[] msgBuffer = "x".getBytes();           //converts entered String into bytes
            try {
                mmOutStream.write(msgBuffer);                //write bytes over BT connection via outstream
            } catch (IOException e) {
                //if you cannot write, close the application
                Toast.makeText(getBaseContext(), "Connection Failure", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
