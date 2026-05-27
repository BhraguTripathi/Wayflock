package com.example.wayflock.domain.model



data class Trip(
    val id : String,
    val name : String,
    val location : String? = null,
    val membersCount : Int? = null,
    val imageUrl : String,
    val isLive : Boolean? = null,
    val status : String? = null,
    val lastUpdated : String? = null,
    val dateRange : String? = null,
    val isCompleted : Boolean = false
)