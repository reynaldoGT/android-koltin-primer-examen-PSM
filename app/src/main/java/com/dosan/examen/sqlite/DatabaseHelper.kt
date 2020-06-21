package com.dosan.examen.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    HistorialContract.Companion.Entrada.NOMBRE_TABLA,
    null,
    HistorialContract.Companion.VERSION
) {

    companion object {
        val CREATE_HISTORIAL_TABLA =
            "create table ${HistorialContract.Companion.Entrada.NOMBRE_TABLA}(" +
                    "${HistorialContract.Companion.Entrada.COLUMNA_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${HistorialContract.Companion.Entrada.COLUMNA_PESO} TEXT," +
                    "${HistorialContract.Companion.Entrada.COLUMNA_ALTURA} TEXT," +
                    "${HistorialContract.Companion.Entrada.COLUMNA_RESULTADO} TEXT," +
                    "${HistorialContract.Companion.Entrada.COLUMNA_TIPO} TEXT)"

        val REMOVE_HISTORIAL_TABLA =
            "DROP TABLE IF EXISTS " + HistorialContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_HISTORIAL_TABLA)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_HISTORIAL_TABLA)
        onCreate(db)
    }

}