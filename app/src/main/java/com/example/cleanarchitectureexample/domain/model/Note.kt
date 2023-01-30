package com.example.cleanarchitectureexample.domain.model

class Note(
    val id: Int = DEFAULT_ID,
    val title: String,
    val desc: String,
    val createdAt: Long
) {
    companion object {
        const val DEFAULT_ID = 0
    }
}