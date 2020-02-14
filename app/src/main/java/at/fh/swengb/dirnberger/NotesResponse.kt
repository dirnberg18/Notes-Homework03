package at.fh.swengb.dirnberger

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class NotesResponse(val lastSync: Long, val notes: List<Note>){}