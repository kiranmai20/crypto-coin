package com.example.cryptocoin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Urls : Serializable {
    @SerializedName("website")
    @Expose
    var website: List<String>? = null
    @SerializedName("technical_doc")
    @Expose
    var technicalDoc: List<String>? = null
    @SerializedName("twitter")
    @Expose
    var twitter: List<String>? = null
    @SerializedName("reddit")
    @Expose
    var reddit: List<String>? = null
    @SerializedName("message_board")
    @Expose
    var messageBoard: List<String>? = null
    @SerializedName("announcement")
    @Expose
    var announcement: List<String>? = null
    @SerializedName("chat")
    @Expose
    var chat: List<String>? = null
    @SerializedName("explorer")
    @Expose
    var explorer: List<String>? = null
    @SerializedName("source_code")
    @Expose
    var sourceCode: List<String>? = null

    companion object {
        private const val serialVersionUID = -2758928769266371528L
    }
}