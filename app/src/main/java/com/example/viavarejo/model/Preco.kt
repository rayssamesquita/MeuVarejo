package com.example.viavarejo.model

import java.io.Serializable

class Preco(val planoPagamento : String,
            val valorParcela : Float,
            val quantidadeMaximaParcelas : Integer,
            val precoAtual : Float,
            val precoAnterior : Float,
            val porcentagemDesconto : Float,
            val descontoFormaPagamento : String) : Serializable

