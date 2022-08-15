package com.example.poccoroutines.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryResponse(
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("forks_count")
    var forksCount: Int,
    @SerializedName("owner")
    var owner: OwnerResponse,
    @SerializedName("license")
    var license: LicenseResponse
) : Parcelable

@Parcelize
data class OwnerResponse(
    @SerializedName("login")
    var login : String,
    @SerializedName("avatar_url")
    var avatarUrl : String,
    @SerializedName("url")
    var url : String
) : Parcelable

@Parcelize
data class LicenseResponse(
    @SerializedName("name")
    var name : String,
    @SerializedName("key")
    var key : String,
    @SerializedName("url")
    var url : String
) : Parcelable