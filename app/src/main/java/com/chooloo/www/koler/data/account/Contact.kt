package com.chooloo.www.koler.data.account

data class Contact(
    val id: Long = 0,
    val name: String? = null,
    val photoUri: String? = null,
    val starred: Boolean = false,
    val lookupKey: String? = null,
) {
    override fun toString() = "Contact with id:$id name:$name"

    companion object {
        val UNKNOWN = Contact(name = "Unknown")
    }

    override fun equals(other: Any?) = other is Contact && id == other.id
}

