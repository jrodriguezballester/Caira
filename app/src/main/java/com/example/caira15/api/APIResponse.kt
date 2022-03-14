package com.example.caira15.api

import com.example.caira15.model.Result
import com.example.caira15.model.User
import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("status") var status: Int,
    @SerializedName("msg") var msg: String,
    @SerializedName("result") var result: List<User>,

    )
data class APIResponseLogin(
    @SerializedName("status") var status: Int,
    @SerializedName("msg") var msg: String,
    @SerializedName("result") var result:Result,

    )
data class APIResponseSingUpUser(
    @SerializedName("status") var status: Int,
    @SerializedName("msg") var msg: String,
    @SerializedName("result") var result: Any,
    )