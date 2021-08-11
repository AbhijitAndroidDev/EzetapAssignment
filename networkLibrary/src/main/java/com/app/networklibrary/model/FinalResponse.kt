package com.app.networklibrary.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FinalResponse(



	@field:SerializedName("uidata")
	val uidata: ArrayList<UidataItem>,

	@field:SerializedName("logo-url")
	val logoUrl: String,

	@field:SerializedName("heading-text")
	val headingText: String
):Serializable

data class UidataItem(

	@field:SerializedName("uitype")
	val uitype: String,

	@field:SerializedName("value")
	val value: String,

	@field:SerializedName("hint")
	val hint: String,

	@field:SerializedName("key")
	val key: String
):Serializable
