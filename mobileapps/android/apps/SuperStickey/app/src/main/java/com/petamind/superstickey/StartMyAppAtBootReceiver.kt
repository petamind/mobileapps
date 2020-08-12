package com.petamind.superstickey

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StartMyAppAtBootReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        if(Intent.ACTION_BOOT_COMPLETED.equals(p1?.action)){
            val i = Intent(p0, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            p0?.startActivity(Intent(p0, MainActivity::class.java))
        }
    }
}