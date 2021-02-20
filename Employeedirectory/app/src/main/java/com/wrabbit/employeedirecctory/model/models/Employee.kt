package com.wrabbit.employeedirecctory.model.models

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employee_table")
data class Employee(
    @Expose
    @PrimaryKey
    val id:Int,
    @Expose
    val name:String,
    @Expose
    val username:String,
    @Expose
    val email:String,
    @Expose
    val profile_image:String,

    @Embedded
    @SerializedName("address") var address : Address?,

    @ColumnInfo(name="phone",defaultValue = "Not available")
    @SerializedName("phone") var phone : String?,
    @ColumnInfo(name="website")
    @SerializedName("website") var website : String?,
    @Embedded
    @SerializedName("company") var company : Company?
    )

data class Geo (

    @SerializedName("lat") var lat : String,
    @SerializedName("lng") var lng : String

)

data class Address (

    @SerializedName("street") var street : String,
    @SerializedName("suite") var suite : String,
    @SerializedName("city") var city : String,
    @SerializedName("zipcode") var zipcode : String,
    @Embedded
    @SerializedName("geo") var geo : Geo

)

data class Company (

    @ColumnInfo(name = "companyname", defaultValue = "Not available")
    @SerializedName("name") var companyname : String?,
    @SerializedName("catchPhrase") var catchPhrase : String?,
    @SerializedName("bs") var bs : String?

)