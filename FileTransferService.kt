package com.example.zvonimir.testwp2p

import android.app.IntentService
import android.content.Intent
import java.net.InetSocketAddress
import java.net.Socket

class FileTransferService : IntentService("File Transfer") {
    var copy: String="ACTION_SEND_FILE"
    var EXTRAS_GROUP_OWNER_ADRESS: String="go_host"
    var EXTRAS_GROUP_OWNER_PORT: String="go_port"

    override fun onHandleIntent(p0: Intent?) {
            if(p0?.action==copy){
                var context=applicationContext
                var  host=p0.getStringExtra(EXTRAS_GROUP_OWNER_ADRESS)
                var socket= Socket()
                var port=p0.getIntExtra(EXTRAS_GROUP_OWNER_PORT,0)
                socket.bind(null)
                socket.connect(InetSocketAddress(host,port),5000)
                var out=socket.getOutputStream()
            }
    }
}