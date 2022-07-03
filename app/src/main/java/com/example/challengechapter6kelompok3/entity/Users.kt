package com.example.challengechapter6kelompok3.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Users(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    @ColumnInfo(name = "username")
    var username: String,
    var password: String,
    var nama: String,
    var email: String
) : Parcelable