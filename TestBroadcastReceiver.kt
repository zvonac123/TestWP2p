package com.example.zvonimir.testwp2p

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.p2p.WifiP2pConfig
import android.net.wifi.p2p.WifiP2pManager
import android.widget.Toast

class TestBroadcastReceiver (manager: WifiP2pManager?,channel: WifiP2pManager.Channel?,activity: MainActivity?) : BroadcastReceiver(){
    private var manager: WifiP2pManager?=manager
    private var channel: WifiP2pManager.Channel?=channel
    private var activity=activity

    override fun onReceive(p0: Context?, p1: Intent?) {
        if(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION==(p1?.action)){
            manager?.discoverPeers(channel,null)
        }
        if(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION==(p1?.action)){
            manager?.requestPeers(channel,{
                DeviceList.deviceList=it.deviceList
                connect()
            })
        }
    }

    fun connect(){
        var iterator=DeviceList.deviceList.iterator()
        if(iterator.hasNext()==true){
            var device=iterator.next()
            var config=WifiP2pConfig()
            config.deviceAddress=device.deviceAddress
            manager?.connect(channel,config,TestListener(activity!!.applicationContext))
        }
    }
}