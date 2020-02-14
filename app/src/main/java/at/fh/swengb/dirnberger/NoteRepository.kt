package at.fh.swengb.dirnberger

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object NoteRepository{

    fun getNotes(token: String, lastSync: Long, success:(noteResponse: NotesResponse)->Unit,error:(errorMessage:String)->Unit){
        NoteApi.retrofitService.notes(token, lastSync).enqueue(object : Callback<NotesResponse>{

            override fun onFailure(call: Call<NotesResponse>, t: Throwable){
                error("Failed Call")
            }
            override fun onResponse(call: Call<NotesResponse>, response: Response<NotesResponse>){
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    success(responseBody)
                } else {
                    error("Error, try again!")
                }
            }
        })
    }
    fun uploadNote (token: String, note2Upload: Note, success: (note: Note)->Unit, error: (errorMessage: String)->Unit){
        NoteApi.retrofitService.addOrUpdateNote(token, note2Upload).enqueue(object : Callback<Note>{

            override fun onFailure(call: Call<Note>, t: Throwable){
                error("Failed Call! " + t.localizedMessage)}

            override fun onResponse(call: Call<Note>, response: Response<Note>){
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null){
                    success(responseBody)
                } else{
                    error("Error, try again " + response.message())
                }
            }
        })
    }
    fun addNote(context: Context, newNote: Note){
        val database= NoteDatabase.getDatabase(context)
        database.NoteDao.insert(newNote)}

    fun getNoteById (context: Context, id: String):Note{
        val database= NoteDatabase.getDatabase(context)
        return database.NoteDao.findNoteById(id)}

    fun getNotesAll (context: Context):List<Note>{
        val database= NoteDatabase.getDatabase(context)
        return database.NoteDao.getNotesAll()}

    fun clearDb (context: Context){
        val database= NoteDatabase.getDatabase(context)
        return database.NoteDao.deleteAllNotes()}
}