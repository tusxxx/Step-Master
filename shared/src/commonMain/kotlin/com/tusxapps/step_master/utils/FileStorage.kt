package com.tusxapps.step_master.utils

interface FileStorage {
    fun readFile(fileName: String): ByteArray?
    fun writeFile(fileName: String, content: ByteArray)
}