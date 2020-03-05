package es.iessaladillo.pedrojoya.quilloque.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.iessaladillo.pedrojoya.quilloque.room.Call
import es.iessaladillo.pedrojoya.quilloque.room.RecentCalls

@Dao
interface CallDao {

    @Query("SELECT C.id AS callId, C.phoneNumber AS phoneNumber, C.type AS type, \n" +
            "C.date AS date, C.time AS time, T.id AS contactId, T.name AS contactName \n" +
            "FROM Call AS C LEFT JOIN Contact AS T ON C.phoneNumber = T.phoneNumber \n" +
            "ORDER BY C.id DESC LIMIT :limit")
    fun queryRecentCalls(limit: Int): LiveData<List<RecentCalls>>

    @Insert
    fun insertCall(call: Call)

    @Delete
    fun deleteCall(call: Call): Int

}