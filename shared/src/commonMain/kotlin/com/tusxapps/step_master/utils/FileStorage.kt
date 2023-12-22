package com.tusxapps.step_master.utils

interface FileStorage {
    suspend fun readFile(fileName: String): ByteArray?
    suspend fun writeFile(fileName: String, content: ByteArray)
}