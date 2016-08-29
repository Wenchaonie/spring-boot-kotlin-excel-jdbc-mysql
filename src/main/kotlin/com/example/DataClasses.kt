package com.example

import org.litote.kmongo.MongoId
import org.springframework.data.mongodb.core.mapping.Document

data class WowUser(
        @MongoId
        val id: org.bson.types.ObjectId?,

        val default_address: Int,
        val default_billing_address: Int,
        val name: String,
        val mobile: String,
        val password: String,
        val email: String,
        val balance: Int,
        val status: String,
        val created_at: String,
        val updated_at: String,
        val order_count: Int,
        val nick: String,
        val headimage: String,
        val desc: String,
        val sex: String
)

