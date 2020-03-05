package es.iessaladillo.pedrojoya.quilloque.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String
)

@Entity(
    tableName = "call",
    foreignKeys = [
        ForeignKey(
            entity = Contact::class,
            parentColumns = ["id"],
            childColumns = ["contactId"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class Call (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "contactName")
    val contactName: String,
    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,
    @ColumnInfo(name = "type")
    val type: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "contactId")
    val contactId: Long?
)