package com.example.cleanarchitectureexample.domain.model

class Note(
    val id: Int = DEFAULT_ID,
    val title: String? = null,
    val desc: String? = null,
    val createdAt: Long? = null
) {
    companion object {
        const val DEFAULT_ID = 0
    }
}