package com.dosan.examen.Crud


class Historial(id: Int, peso: String, altura: String, resultado: String, tipo: String) {

    var id: Int = 0
    var peso: String = ""
    var altura: String = ""
    var resultado: String = ""
    var tipo: String = ""

    init {
        this.id = id
        this.peso = peso
        this.altura = altura
        this.resultado = resultado
        this.tipo = tipo
    }
}