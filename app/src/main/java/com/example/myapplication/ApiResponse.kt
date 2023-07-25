package com.example.myapplication
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("name")
    val name: Name? = null,

    @SerializedName("flags")
    val flags: Flags? = null
)

data class Name(
    @SerializedName("common")
    val common: String? = null,

    @SerializedName("official")
    val official: String? = null,

    @SerializedName("nativeName")
    val nativeName: NativeName? = null
)

data class Flags(
    @SerializedName("png")
    val png: String? = null,

    @SerializedName("svg")
    val svg: String? = null
)

data class NativeName(
    @SerializedName("eng")
    val eng: Eng? = null
)

data class Eng(
    @SerializedName("official")
    val official: String? = null,

    @SerializedName("common")
    val common: String? = null
)
