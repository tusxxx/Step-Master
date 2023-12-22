package com.tusxapps.step_master.utils

import android.content.Context
import io.ktor.utils.io.core.use
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream

class AndroidFileStorage(private val context: Context) : FileStorage {
    override suspend fun readFile(fileName: String): ByteArray? = withContext(Dispatchers.IO) {
        try {
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

    override suspend fun writeFile(fileName: String, content: ByteArray) = withContext(Dispatchers.IO) {
        try {
           context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(content)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}