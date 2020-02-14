package at.fh.swengb.dirnberger

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AuthRequest(val username: String, val password: String){}