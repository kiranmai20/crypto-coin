package com.example.cryptocoin.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoin.R
import com.example.cryptocoin.adapter.CoinBaseAdapter
import com.example.cryptocoin.log.AppLog
import com.example.cryptocoin.model.CryptoList
import com.example.cryptocoin.model.Datum
import com.example.cryptocoin.network.APIClient
import com.example.cryptocoin.network.APIInterface
import com.example.cryptocoin.util.Util
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    var coinRecycler: RecyclerView? = null
    var horizontalLayoutManager: LinearLayoutManager? = null
    var adapter: CoinBaseAdapter? = null
    var cryptoList = mutableListOf<Datum>()
    var apiInterface: APIInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        coinRecycler = findViewById(R.id.recyclerview)
        horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        coinRecycler!!.layoutManager = horizontalLayoutManager
        apiInterface = APIClient.client?.create(APIInterface::class.java)
        adapter = CoinBaseAdapter(cryptoList)
        coinRecycler?.adapter = adapter
        if (Util.isNetworkAvailable(applicationContext))
            getCoinList()
        else
            Toast.makeText(
                this@HomeActivity,
                "No Network,Please try again.",
                Toast.LENGTH_SHORT
            ).show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                if (Util.isNetworkAvailable(applicationContext)) {
                    progress.visibility = View.VISIBLE
                    getCoinList()
                }
                else
                    Toast.makeText(
                        this@HomeActivity,
                        "No Network,Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                return true
            }
            R.id.menu_clear -> {
                adapter?.refreshLis(cryptoList)
                adapter!!.notifyDataSetChanged()
                return true
            }
        }
        return true
    }

    private fun getCoinList() {
        val call2: Call<CryptoList> = apiInterface?.doGetUserList("50") as Call<CryptoList>
        call2.enqueue(object : Callback<CryptoList?> {
            override fun onResponse(
                call: Call<CryptoList?>,
                response: Response<CryptoList?>
            ) {
                progress.visibility = View.GONE
                val list: CryptoList? = response.body()
                adapter?.refreshLis(list?.data)
                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(
                call: Call<CryptoList?>,
                t: Throwable
            ) {
                progress.visibility = View.GONE
                Toast.makeText(this@HomeActivity, "onFailure", Toast.LENGTH_SHORT).show()
                AppLog.d("onFailure", t.localizedMessage)
                call.cancel()
            }
        })
    }

}

