package com.dosan.examen.Crud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.dosan.examen.sqlite.DatabaseHelper
import com.dosan.examen.sqlite.HistorialContract

class HistorialCrud(context: Context) {
    private var helper: DatabaseHelper? = null

    init {
        helper = DatabaseHelper(context)
    }

    fun newHistory(item: Historial) {
        //Funcion que nso permite escribir en una base de datos
        val db: SQLiteDatabase = helper?.writableDatabase!!
        //Mapeo con los datos a insertar
        val values = ContentValues()

        values.put(HistorialContract.Companion.Entrada.COLUMNA_PESO, item.peso)
        values.put(HistorialContract.Companion.Entrada.COLUMNA_ALTURA, item.altura)
        values.put(HistorialContract.Companion.Entrada.COLUMNA_RESULTADO, item.resultado)
        values.put(HistorialContract.Companion.Entrada.COLUMNA_TIPO, item.tipo)

        //Insertamos una fila en la tabla
        val newRowId = db.insert(HistorialContract.Companion.Entrada.NOMBRE_TABLA, null, values)
        db.close()
    }

    fun getHistorial(): ArrayList<Historial> {
        var items: ArrayList<Historial> = ArrayList()

        // Abrir la cbase de datos en mode de lectura
        var db: SQLiteDatabase = helper?.readableDatabase!!

        //Especificar columnas que quiero consultar
        var columnas = arrayOf(
            HistorialContract.Companion.Entrada.COLUMNA_ID,
            HistorialContract.Companion.Entrada.COLUMNA_PESO,
            HistorialContract.Companion.Entrada.COLUMNA_ALTURA,
            HistorialContract.Companion.Entrada.COLUMNA_RESULTADO,
            HistorialContract.Companion.Entrada.COLUMNA_TIPO
        )

        //Crear un cros para recorre la tabla
        var c: Cursor = db.query(
            HistorialContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )
        //Log.d("consulta",c.)
        // Hacer un recorrido del cursor en la tabla
        while (c.moveToNext()) {
            items.add(
                Historial(
                    c.getInt((c.getColumnIndexOrThrow(HistorialContract.Companion.Entrada.COLUMNA_ID))),
                    c.getString((c.getColumnIndexOrThrow(HistorialContract.Companion.Entrada.COLUMNA_PESO))),
                    c.getString((c.getColumnIndexOrThrow(HistorialContract.Companion.Entrada.COLUMNA_ALTURA))),
                    //c.getString((c.getColumnIndexOrThrow(HistorialContract.Companion.Entrada.COLUMNA_TIPO))),
                    c.getString((c.getColumnIndexOrThrow(HistorialContract.Companion.Entrada.COLUMNA_RESULTADO))),
                    c.getString((c.getColumnIndexOrThrow(HistorialContract.Companion.Entrada.COLUMNA_TIPO)))
                )
            )
        }
        //Cerrar DB
        db.close()
        return items
    }

    fun deleteItem(id: Int) {
        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(
            HistorialContract.Companion.Entrada.NOMBRE_TABLA,
            " id = ?",
            arrayOf(id.toString())
        )
        db.close()
    }
    //Borrar todos los elemetnso de nuestra base de datos
    fun deleteHistorial() {
        val db: SQLiteDatabase = helper?.writableDatabase!!
        db.execSQL("DELETE FROM " + HistorialContract.Companion.Entrada)
        db.close()
    }
}