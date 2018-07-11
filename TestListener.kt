package com.example.zvonimir.testwp2p

import android.content.Context
import android.net.wifi.p2p.WifiP2pManager
import android.widget.Toast

class TestListener(context: Context) : WifiP2pManager.ActionListener{
    var context=context

    override fun onSuccess() {
        Toast.makeText(context,"Connected",Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(p0: Int) {
        Toast.makeText(context,p0.toString(),Toast.LENGTH_SHORT).show()
    }
}