package com.example.viavarejo

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.viavarejo.adapters.ProdutoListAdapter
import com.example.viavarejo.model.Detalhes
import com.example.viavarejo.model.Produto
import com.example.viavarejo.model.ViuComprou
import com.example.viavarejo.model.ViuComprouListAdapter
import com.example.viavarejo.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.content_detalhes.*
import kotlinx.android.synthetic.main.fragment_produto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class ProdutoDetalhesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        val produto = intent.getSerializableExtra("produto") as? Produto

        val imagem : ImageView = findViewById(R.id.detalhe_imagem)
        val titulo : TextView = findViewById(R.id.detalhe_titulo)
        val descricao : TextView = findViewById(R.id.detalhe_descricao)
        val precoOld : TextView = findViewById(R.id.detalhe_preco_old)
        val preco : TextView = findViewById(R.id.detalhe_preco)
        val parcelas : TextView = findViewById(R.id.detalhe_parcela)
        val titulo1 : TextView = findViewById(R.id.detalhe_titulo1)
        val resposta1 : TextView = findViewById(R.id.detalhe_resposta1)
        val titulo2 : TextView = findViewById(R.id.detalhe_titulo2)
        val resposta2 : TextView = findViewById(R.id.detalhe_resposta2)
        val titulo3 : TextView = findViewById(R.id.detalhe_titulo3)
        val resposta3 : TextView = findViewById(R.id.detalhe_resposta3)
        val score : TextView = findViewById(R.id.detalhe_score)
        val avaliacao : TextView = findViewById(R.id.detalhe_total_avaliacoes)
        val usuario : TextView = findViewById(R.id.detalhe_user)
        val comentario : TextView = findViewById(R.id.detalhe_comentario)
        val format : NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))


        precoOld.paintFlags = precoOld.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        Glide.with(this).load(produto?.imagemUrl).into(imagem)
        titulo.text = produto?.nome
        descricao.text = produto?.descricao
        precoOld.text = format.format(produto?.preco?.precoAnterior)
        preco.text = format.format(produto?.preco?.precoAtual)
        parcelas.text = produto?.preco?.planoPagamento


        score.text = "4.5"
        avaliacao.text = "(750 avaliações)"
        usuario.text = "Alexandre"
        comentario.text = "Produto maravilhoso!!!"

        val call = RetrofitInitializer().produtosService().detalhes()
        call.enqueue(object : Callback<Detalhes> {
            override fun onResponse(call: Call<Detalhes?>,
                                    response: Response<Detalhes>
            ) {
                response.body()?.let {
                    val detalhes: Detalhes = it
                    titulo1.text = detalhes.maisInformacoes.get(0).valores.get(0).nome
                    titulo2.text = detalhes.maisInformacoes.get(0).valores.get(1).nome
                    titulo3.text = detalhes.maisInformacoes.get(0).valores.get(3).nome

                    resposta1.text = detalhes.maisInformacoes.get(0).valores.get(0).valor
                    resposta2.text = detalhes.maisInformacoes.get(0).valores.get(1).valor
                    resposta3.text = detalhes.maisInformacoes.get(0).valores.get(3).valor
                }
            }

            override fun onFailure(call: Call<Detalhes>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })

        val call2 = RetrofitInitializer().produtosService().viuComprou()
        call2.enqueue(object : Callback<List<ViuComprou>> {
            override fun onResponse(call2: Call<List<ViuComprou>?>,
                                    response: Response<List<ViuComprou>>) {
                response.body()?.let {
                    val produtos: List<ViuComprou> = it
                    setProdutosList(produtos)
                }
            }

            override fun onFailure(call2: Call<List<ViuComprou>>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun setProdutosList(produtos: List<ViuComprou>) {
        val recyclerView = viucomprou_recyclerview
        recyclerView.adapter = ViuComprouListAdapter(produtos, this)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
    }
}
