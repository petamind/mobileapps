package com.petamind.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? {
        return value?.let {
            Instant.ofEpochMilli(it)
                .atZone(ZoneId.of("UTC")).toLocalDateTime()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? {
        return date?.atOffset(ZoneOffset.UTC)?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun fromByteArray(bitmapdata: ByteArray?): Bitmap? {
        return bitmapdata?.let {
            BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.size)
        }
    }

    @TypeConverter
    fun dateToByteArray(bmp: Bitmap?): ByteArray? {
        val stream = ByteArrayOutputStream()
        bmp?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()
        return byteArray
    }
}