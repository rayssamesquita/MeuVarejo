package com.example.viavarejo.ui.produto

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.viavarejo.R
import com.example.viavarejo.adapters.ProdutoListAdapter
import com.example.viavarejo.model.Produto
import com.example.viavarejo.model.ProdutoList
import com.example.viavarejo.retrofit.RetrofitInitializer
import kotlinx.android.synthetic.main.fragment_produto.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_produto, container, false)

        val call = RetrofitInitializer().produtosService().listar()
        call.enqueue(object : Callback<ProdutoList> {
            override fun onResponse(call: Call<ProdutoList?>,
                                    response: Response<ProdutoList>) {
                response.body()?.let {
                    val produtos: ProdutoList = it
                    setProdutosList(produtos.produtos)
                }
            }

            override fun onFailure(call: Call<ProdutoList>, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
        return root
    }

    private fun setProdutosList(produtos: List<Produto>) {
        val recyclerView = produtos_recyclerview
        recyclerView.adapter = ProdutoListAdapter(produtos, requireContext())
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
}