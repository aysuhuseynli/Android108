package com.example.android108.model

import com.google.gson.annotations.SerializedName

data class AuthRequestBody(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("expiresInMins")
	val expiresInMins: Int? = null
)
