package br.com.curso.model

import java.math.BigDecimal

data class Parcela(
    val valor: BigDecimal,
    var dataVencimento: String
)