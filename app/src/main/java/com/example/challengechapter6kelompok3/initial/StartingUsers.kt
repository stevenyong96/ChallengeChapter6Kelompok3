package com.example.challengechapter6kelompok3.initial

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.challengechapter6kelompok3.R
import com.example.challengechapter6kelompok3.UserActivity
import com.example.challengechapter6kelompok3.database.UserDatabase
import com.example.challengechapter6kelompok3.entity.Users
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingUsers(private val context : Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase){
        super.onCreate(db)
    }

    private fun loadJSONArray(context: Context): JSONArray?{

        val inputStream = context.resources.openRawResource(R.raw.user_initial)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }

    private suspend fun fillWithStartingNotes(context: Context){

        val dao = UserDatabase.getInstance(context)?.userDao()

        try {
            val notes = loadJSONArray(context)
            if (notes != null){
                for (i in 0 until notes.length()){
                    val item = notes.getJSONObject(i)
                    val userUsername = item.getString("username")
                    val userPassword = item.getString("password")
                    val userNama = item.getString("nama")
                    val userEmail = item.getString("email")

                    val usersEntity = Users(
                        i,userUsername,userPassword,userNama,userEmail
                    )

                    dao?.insertUser(usersEntity)
                }
            }
        }

        catch (e: JSONException) {
            Log.d(UserActivity::class.simpleName,"something went wrong , ${e}")
        }
    }

}