package at.fh.swengb.dirnberger

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity
class Note(@PrimaryKey val id: String, var title: String, var text: String, var toUpload: Boolean){}