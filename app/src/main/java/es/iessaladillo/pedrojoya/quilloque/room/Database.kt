package es.iessaladillo.pedrojoya.quilloque.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import es.iessaladillo.pedrojoya.quilloque.room.daos.ContactDao

@Database(entities = [Contact::class], version = 1, exportSchema = true)
abstract class StroopDatabase : RoomDatabase() {

    abstract val contactDao: ContactDao

    companion object {

        @Volatile
        private var INSTANCE: StroopDatabase? = null

        fun getInstance(context: Context): StroopDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            StroopDatabase::class.java,
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