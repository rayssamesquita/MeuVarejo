package com.example.viavarejo.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.viavarejo.MainActivity
import com.example.viavarejo.ProdutoDetalhesActivity
import com.example.viavarejo.R
import com.example.viavarejo.model.Produto
import kotlinx.android.synthetic.main.produto_item.view.*
import java.text.NumberFormat
import java.util.*


class ProdutoListAdapter(private val produtos: List<Produto>, private val context: Context) : RecyclerView.Adapter<ProdutoListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produtos = produtos[position]
        holder?.let {it.bindView(produtos)}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(produto: Produto) {
            val card = itemView.produto_card
            val imagem = itemView.produto_image
            val titulo = itemView.produto_title
            val precoOld = itemView.produto_preco_old
            val preco = itemView.produto_preco

            precoOld.paintFlags = precoOld.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            val format : NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

            Glide.with(itemView).load(produto.imagemUrl).into(imagem)
            card.setOnClickListener {
                val intent = Intent(itemView.context, ProdutoDetalhesActivity::class.java).apply {
                    putExtra("produto", produto)
                }
                itemView.context.startActivity(intent)
            }
            titulo.text = produto.nome
            precoOld.text = format.format(produto.preco.precoAnterior)
            preco.text = format.format(produto.preco.precoAtual)
        }

    }

}