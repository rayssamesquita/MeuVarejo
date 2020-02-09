package com.example.viavarejo.retrofit

import com.example.viavarejo.model.Detalhes
import com.example.viavarejo.model.ProdutoList
import com.example.viavarejo.model.ViuComprou
import retrofit2.Call
import retrofit2.http.GET

interface ProdutoService {

    @GET("5d1b4f0f34000074000006dd")
    fun listar() : Call<ProdutoList>

    @GET("5d1b4fd23400004c000006e1")
    fun detalhes() : Call<Detalhes>

    @GET("5d1b507634000054000006ed")
    fun viuComprou() : Call<List<ViuComprou>>
}