package com.example.viavarejo.model

class Detalhes(val id : Int,
              val nome : String,
              val descricao : String,
              val retiraEmLoja : Boolean,
              val maisInformacoes : List<Info>)