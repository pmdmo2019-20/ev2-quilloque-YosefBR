package es.iessaladillo.pedrojoya.quilloque.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.quilloque.room.daos.CallDao
import es.iessaladillo.pedrojoya.quilloque.room.daos.ContactDao

@Database(entities = [Contact::class, Call::class], version = 1, exportSchema = true)
abstract class TlfDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao
    abstract val callDao: CallDao

    companion object {

        @Volatile
        private var INSTANCE: TlfDatabase? = null

        fun getInstance(context: Context): TlfDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TlfDatabase::class.java,
                            "tlf_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}