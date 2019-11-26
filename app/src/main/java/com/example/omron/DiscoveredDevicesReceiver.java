package com.example.omron;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiscoveredDevicesReceiver extends BroadcastReceiver {
    public DiscoveredDevicesReceiver(ListView listView)
    {
        lv = listView;
    }
    ListView lv;
    public ArrayList<String> deviceNames = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action))
        {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            String deviceName = device.getName() + " " + device.getAddress();
            deviceNames.add(deviceName);
        }
        else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
        {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(lv.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, deviceNames);
            lv.setAdapter(adapter);
        }
    }
}
