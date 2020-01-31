package com.example.cryptocoin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoin.R
import com.example.cryptocoin.model.Datum
import com.example.cryptocoin.ui.CoinInfoActivity

class CoinBaseAdapter(var coinList: List<Datum>?) :
    RecyclerView.Adapter<CoinBaseAdapter.ViewHolder>() {

    override fun getItemCount() = coinList!!.size

    private var mContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        this.mContext = parent.context;

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.coin_item,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val coin = coinList!!.get(position)

        holder.textViewTitle.text = coin.name
        holder.textViewMarketCap.text =
            "Market Cap: $" + String.format("%,d", Math.round(coin.quote?.uSD?.marketCap!!))
        holder.textViewVolume.text = "Volume/24h: $" + java.lang.String.format(
            "%,d",
            Math.round(coin.quote?.uSD?.volume24h!!)
        )
        holder.textViewPrice.text =
            "Price: $" + java.lang.String.format("%,f", coin.quote?.uSD?.price)

        holder.lyt.setOnClickListener {
            var intent = Intent(mContext, CoinInfoActivity::class.java)
            intent.putExtra("ID", coin.id)
            mContext?.startActivity(intent)
        }

    }

    /**
     * Set the updated list to adater
     * **/
    fun refreshLis(cryptoList: List<Datum>?) {
        this.coinList = cryptoList
        notifyDataSetChanged()

    }

    /**
     * ViewHolder class for CoinBase adapter
     * **/
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var textViewTitle: TextView
        internal var textViewVolume: TextView
        internal var textViewMarketCap: TextView
        internal var textViewPrice: TextView
        internal var lyt: ConstraintLayout

        init {

            textViewTitle = itemView.findViewById(R.id.title) as TextView
            textViewVolume = itemView.findViewById(R.id.volume) as TextView
            textViewMarketCap = itemView.findViewById(R.id.market_cap) as TextView
            textViewPrice = itemView.findViewById(R.id.price) as TextView
            lyt = itemView.findViewById(R.id.lyt) as ConstraintLayout
        }
    }
}