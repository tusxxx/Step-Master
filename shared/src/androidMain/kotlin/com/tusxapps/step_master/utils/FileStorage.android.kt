package com.tusxapps.step_master.utils

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream

class AndroidFileStorage(private val context: Context) : FileStorage {
    override fun readFile(fileName: String): ByteArray? {
        return try {
            val fileInputStream: FileInputStream = context.openFileInput(fileName)
            val bytes = ByteArray(fileInputStream.available())
            fileInputStream.read(bytes)
            fileInputStream.close()
            bytes
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun writeFile(fileName: String, content: ByteArray) {
        try {
            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            fileOutputStream.write(content)
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}