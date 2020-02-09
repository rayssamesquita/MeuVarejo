package com.example.viavarejo.model

import java.io.Serializable

class Produto(val id : Int,
              val sku : Int,
              val nome : String,
              val disponivel : Boolean,
              val descricao : String,
              val imagemUrl : String,
              val classificacao : Int,
              val preco : Preco,
              val quantidade : Int) : Serializable