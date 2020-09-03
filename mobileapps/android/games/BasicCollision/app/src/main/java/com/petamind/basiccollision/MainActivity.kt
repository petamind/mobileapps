package com.petamind.basiccollision

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RectVRect(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.rvsp -> setContentView(RectVPoint(this))
            R.id.rvsr -> setContentView(RectVRect(this))
            R.id.rvsc -> setContentView(RectVCircle(this))
        }
        return super.onOptionsItemSelected(item)
    }
}