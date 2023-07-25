package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("name") val name: Name? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("subregion") val subregion: String? = null,
    @SerializedName("capital") val capital: List<String>? = null,
    @SerializedName("population") val population: Int? = null,
    @SerializedName("currencies") val currencies: Map<String, Currency>? = null,
    @SerializedName("languages") val languages: Map<String, String>? = null,
    @SerializedName("flags") val flags: Flags? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        name = parcel.readParcelable(Name::class.java.classLoader),
        region = parcel.readString(),
        subregion = parcel.readString(),
        capital = parcel.createStringArrayList(),
        population = parcel.readValue(Int::class.java.classLoader) as? Int,
        currencies = parcel.readHashMap(Currency::class.java.classLoader) as? Map<String, Currency>,
        languages = parcel.readHashMap(String::class.java.classLoader) as? Map<String, String>,
        flags = parcel.readParcelable(Flags::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(name, flags)
        parcel.writeString(region)
        parcel.writeString(subregion)
        parcel.writeStringList(capital)
        parcel.writeValue(population)
        parcel.writeMap(currencies as? Map<String, Any>)
        parcel.writeMap(languages as? Map<String, Any>)
        parcel.writeParcelable(this.flags, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<ApiResponse> {
        override fun createFromParcel(parcel: Parcel): ApiResponse = ApiResponse(parcel)
        override fun newArray(size: Int): Array<ApiResponse?> = arrayOfNulls(size)
    }
}

data class Name(
    @SerializedName("common") val common: String? = null,
    @SerializedName("official") val official: String? = null,
    @SerializedName("nativeName") val nativeName: NativeName? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        common = parcel.readString(),
        official = parcel.readString(),
        nativeName = parcel.readParcelable(NativeName::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(common)
        parcel.writeString(official)
        parcel.writeParcelable(nativeName, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Name> {
        override fun createFromParcel(parcel: Parcel): Name = Name(parcel)
        override fun newArray(size: Int): Array<Name?> = arrayOfNulls(size)
    }
}

data class Flags(
    @SerializedName("png") val png: String? = null,
    @SerializedName("svg") val svg: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(png)
        parcel.writeString(svg)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Flags> {
        override fun createFromParcel(parcel: Parcel): Flags = Flags(parcel)
        override fun newArray(size: Int): Array<Flags?> = arrayOfNulls(size)
    }
}

data class NativeName(
    val names: Map<String, NameDetails>,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        names = parcel.readHashMap(NameDetails::class.java.classLoader) as? Map<String, NameDetails> ?: emptyMap()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeMap(names as? Map<String, Any>)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NativeName> {
        override fun createFromParcel(parcel: Parcel): NativeName = NativeName(parcel)
        override fun newArray(size: Int): Array<NativeName?> = arrayOfNulls(size)
    }
}

data class NameDetails(
    @SerializedName("official")
    val official: String? = null,
    @SerializedName("common")
    val common: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        official = parcel.readString(),
        common = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(official)
        parcel.writeString(common)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<NameDetails> {
        override fun createFromParcel(parcel: Parcel): NameDetails = NameDetails(parcel)
        override fun newArray(size: Int): Array<NameDetails?> = arrayOfNulls(size)
    }
}

data class Currency(
    @SerializedName("name") val name: String? = null,
    @SerializedName("symbol") val symbol: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(symbol)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Currency> {
            override fun createFromParcel(parcel: Parcel): Currency = Currency(parcel)
            override fun newArray(size: Int): Array<Currency?> = arrayOfNulls(size)
        }
    }