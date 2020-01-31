package com.example.cryptocoin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Info : Serializable {
    @SerializedName("urls")
    @Expose
    var urls: Urls? = null
    @SerializedName("logo")
    @Expose
    var logo: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("symbol")
    @Expose
    var symbol: String? = null
    @SerializedName("slug")
    @Expose
    var slug: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("notice")
    @Expose
    var notice: Any? = null
    @SerializedName("date_added")
    @Expose
    var dateAdded: String? = null
    @SerializedName("tags")
    @Expose
    var tags: List<String>? = null
    @SerializedName("platform")
    @Expose
    var platform: Any? = null
    @SerializedName("category")
    @Expose
    var category: String? = null

    companion object {
        private const val serialVersionUID = 327193035684225751L
    }
}