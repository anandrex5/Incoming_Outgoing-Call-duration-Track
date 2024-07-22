package com.example.incoming_call

data class CallLogEntry(
    val type: String,
    val date: Long,
    val duration: Int,
    val number: String
)