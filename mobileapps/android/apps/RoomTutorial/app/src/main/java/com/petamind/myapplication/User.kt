package com.petamind.myapplication

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "dob") val dob: LocalDateTime? = null,
    @ColumnInfo(name = "photo") val photo: Bitmap? = null
)