package at.fh.swengb.dirnberger

import com.squareup.moshi.JsonClass

@JsonClass (generateAdapter = true)
class AuthResponse(val token: String){}