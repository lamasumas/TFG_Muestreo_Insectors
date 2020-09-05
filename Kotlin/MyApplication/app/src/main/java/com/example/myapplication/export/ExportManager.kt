package com.example.myapplication.export

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.example.myapplication.room.DatabaseRepository
import com.example.myapplication.room.data_classes.SimpleAdvanceRelation
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.FileOutputStream

class ExportManager {
    private val disposables: CompositeDisposable = CompositeDisposable()


    fun exportToCSV(uri: Uri?, context: Context) {

        val dbRepository = DatabaseRepository(context)
        disposables.add(dbRepository.retrieveSightseeing().subscribe { listOfAnimals ->
            uri.also { uri ->
                context.contentResolver.openFileDescriptor(uri!!, "rw").use {
                    FileOutputStream(it!!.fileDescriptor).use {
                        it.write(createFileContent(listOfAnimals).toByteArray())
                        disposables.clear()
                    }
                }
            }
        })
    }

    fun exportToDrive(context: Context) {
        val dbRepository = DatabaseRepository(context)
        disposables.add(dbRepository.retrieveSightseeing().subscribe {

            val outputStream = context.openFileOutput("Avistamientos.csv", Context.MODE_PRIVATE)
            outputStream.write(createFileContent(it).toByteArray())
            outputStream.close()

            val fileLocation = File(context.filesDir, "Avistamientos.csv")
            val path = FileProvider.getUriForFile(context, "com.example.myapplication.fileprovider",fileLocation)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/csv"
            intent.putExtra(Intent.EXTRA_STREAM, path)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(intent)

        })

    }

    private fun  createFileContent(animals:List<SimpleAdvanceRelation>):String{
        var content: StringBuilder = StringBuilder("Id simple,Id Advance,Especie,Longitud,Latitud,Fecha,Hora,Humedad,Temperatura,Altitud,Presion,Indicie UV,Lugar,Pais\n")
        animals .forEach {content.append(it.toString())}
        return content.toString()
    }

}