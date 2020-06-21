package com.dosan.examen.sqlite

import android.provider.BaseColumns

class HistorialContract {
    companion object {
        val VERSION = 1
        class Entrada : BaseColumns {
            companion object {
                val NOMBRE_TABLA = "historial"
                val COLUMNA_ID = "id"
                val COLUMNA_PESO = "peso"
                val COLUMNA_ALTURA = "altura"
                val COLUMNA_RESULTADO = "resultado"
                val COLUMNA_TIPO = "tipo"
            }
        }
    }
}