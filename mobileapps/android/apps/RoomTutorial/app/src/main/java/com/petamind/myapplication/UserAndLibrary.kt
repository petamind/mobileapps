package com.petamind.myapplication

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndLibrary(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val library: Library
)