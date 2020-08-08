package com.petamind.myclipboard

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.widget.Toast

class ClipboardService: Service(), Runnable {

    lateinit var context: Context
    val handler = object :Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            val cb = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val text = cb?.primaryClip?.getItemAt(0)?.text.toString()
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        context = this
        //Monitor the clipboard using a thread
        Thread(this).start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun run() {
        while(true) {
            Thread.sleep(5000)
            handler.sendEmptyMessage(0)
        }
    }

}