package dedy.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {

    private BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
        Handler h2=new Handler();
    MediaPlayer mp= new MediaPlayer();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerReceiver(receiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        mp=MediaPlayer.create(this, R.raw.a);
    }
    protected void onResume() {

        super.onResume();
        //onResume we start our timer so it can start when the app comes from the backgroun
        h2.postDelayed(r2,0);
    }

    Runnable r2=new Runnable() {
        @Override
        public void run() {
            //Your Toast
            h2.postDelayed(r2,0);
            BTAdapter.startDiscovery();
        }
    };


    private final BroadcastReceiver receiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                int rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                TextView rssi_msg = (TextView) findViewById(R.id.textView1);
                rssi_msg.setText(rssi_msg.getText() + "" + rssi + "dBm\n");
                // ini kalau jauh

                if (rssi<-90)
                {
                    mp.start();
                }

            }
        }
    };
}