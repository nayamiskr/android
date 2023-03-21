package com.example.division

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.concurrent.Flow

@Entity(tableName = "ticket")
data class Ticketlist(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val tname: String,
    val name: String,
    val phone: Int


)

@Dao
interface Ticketdao{
    
    @Insert
    fun add(ticketlist: Ticketlist)

    @Query("SELECT * FROM ticket")
    fun readALL(): List<Ticketlist>

    @Delete
    fun delete(ticketlist: Ticketlist)
}

@Database(entities = [Ticketlist::class], version = 2)
abstract class Ticketdb: RoomDatabase(){
    abstract fun ticketdao(): Ticketdao

}