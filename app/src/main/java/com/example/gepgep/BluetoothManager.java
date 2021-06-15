package com.example.gepgep;


import android.bluetooth.BluetoothSocket;

public class BluetoothManager {

    private BluetoothSocket bluetoothSocket;

    private static final BluetoothManager ourInstance = new BluetoothManager();

    public static BluetoothManager getInstance() {
        return ourInstance;
    }

    private BluetoothManager() {
    }

    public void setBluetoothSocket(BluetoothSocket bluetoothSocket){
        this.bluetoothSocket = bluetoothSocket;
    }
}
