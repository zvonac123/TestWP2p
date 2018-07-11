package com.example.zvonimir.testwp2p

import android.content.Context
import android.content.IntentFilter
import android.net.wifi.p2p.WifiP2pManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    lateinit var manager: WifiP2pManager
    lateinit var channel: WifiP2pManager.Channel
    lateinit var testbc: TestBroadcastReceiver
    var filter: IntentFilter= IntentFilter()
    lateinit var view: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager=getSystemService(Context.WIFI_P2P_SERVICE) as WifiP2pManager
        channel=manager.initialize(this, Looper.getMainLooper(),null)
        testbc= TestBroadcastReceiver(manager,channel,this)
        filter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION)
        filter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION)
        filter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION)
        filter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION)
    }

    override fun onResume(){
        super.onResume()
        registerReceiver(testbc,filter)
    }

    override fun onPause(){
        super.onPause()
        unregisterReceiver(testbc)
    }
}
