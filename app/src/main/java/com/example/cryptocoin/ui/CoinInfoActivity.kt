package com.example.cryptocoin.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.cryptocoin.R
import com.example.cryptocoin.log.AppLog
import com.example.cryptocoin.util.Util
import com.example.cryptocoin.model.DataResponse
import com.example.cryptocoin.model.Info
import com.example.cryptocoin.network.APIClient
import com.example.cryptocoin.network.APIInterface
import kotlinx.android.synthetic.main.coin_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinInfoActivity : AppCompatActivity() {


    var title: TextView? = null
    var websiteUrl: TextView? = null
    var techDocurl: TextView? = null
    var desc: TextView? = null
    var logo: ImageView? = null
    var apiInterface: APIInterface? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coin_info)
        var toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        toolbar.title = "Coin Information"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        id = intent.getIntExtra("ID", 0)
        apiInterface = APIClient.client?.create(APIInterface::class.java)
        title = findViewById(R.id.title)
        websiteUrl = findViewById(R.id.wesite_url)
        techDocurl = findViewById(R.id.docurl)
        desc = findViewById(R.id.desc)
        logo = findViewById(R.id.img_coin)

        if (Util.isNetworkAvailable(applicationContext))
            getCoinData()
        else
            Toast.makeText(
                this@CoinInfoActivity,
                "No Network,Please try again.",
                Toast.LENGTH_SHORT
            ).show()


        //Called when the activity has detected the user's click on action bar back

        toolbar?.setNavigationOnClickListener {
            finish()
        }

    }

    /**
     *
     * API call for getting the Crypto coin info data
     * **/
    private fun getCoinData() {
        val call2: Call<DataResponse> = apiInterface?.getCoinById(id) as Call<DataResponse>
        call2.enqueue(object : Callback<DataResponse?> {
            override fun onResponse(
                call: Call<DataResponse?>,
                response: Response<DataResponse?>
            ) {

                progress.visibility = View.GONE
                val list: DataResponse? = response.body()
                val map: Map<String, Info>? = list?.data
                if (map != null) {
                    for ((key, data) in map) {
                        println(key + "/" + data)
                        AppLog.e("name", "" + data.name)

                        //set the values to UI

                        title?.text = data.name
                        if (data.urls?.website?.size!! > 0)
                            websiteUrl?.text = "Website Url : " + data.urls?.website?.get(0)
                        desc?.text = "Description : " + data.description
                        if (data.urls?.technicalDoc?.size!! > 0)
                            techDocurl?.text =
                                "Technical Doc Url : " + data.urls?.technicalDoc?.get(0)
                        logo?.let { Glide.with(applicationContext).load(data.logo).into(it) }
                    }
                }
            }

            override fun onFailure(
                call: Call<DataResponse?>,
                t: Throwable
            ) {
                progress.visibility = View.GONE
                //  Toast.makeText(this@CoinInfoActivity, "onFailure", Toast.LENGTH_SHORT).show()
                AppLog.d("onFailure", t.localizedMessage)
                call.cancel()
            }
        })
    }

}