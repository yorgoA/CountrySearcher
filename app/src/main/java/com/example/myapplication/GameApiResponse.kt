package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class QuizResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("published") val published: Boolean,
    @SerializedName("questions") val questions: ArrayList<Question>?
) : Parcelable {
    // Implement Parcelable methods and companion object just like you did in the ApiResponse class
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.createTypedArrayList(Question.CREATOR)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeByte(if (published) 1 else 0)
        parcel.writeTypedList(questions)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuizResponse> {
        override fun createFromParcel(parcel: Parcel): QuizResponse {
            return QuizResponse(parcel)
        }

        override fun newArray(size: Int): Array<QuizResponse?> {
            return arrayOfNulls(size)
        }
    }
}

data class Question(
    @SerializedName("id") val id: String,
    @SerializedName("question") val questionText: String,
    @SerializedName("correctAnswer") val correctAnswer: String,
    @SerializedName("incorrectAnswers") val incorrectAnswers: List<String>
) : Parcelable {
    // Implement Parcelable methods and companion object just like you did in the ApiResponse class
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(questionText)
        parcel.writeString(correctAnswer)
        parcel.writeStringList(incorrectAnswers)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}

