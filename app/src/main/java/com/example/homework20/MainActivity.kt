package com.example.homework20

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.homework20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private var statusLoadImages = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        initNetworkBroadcastReceiver()

        setContentView(view)
    }
    private fun initNetworkBroadcastReceiver(){

        val networkChangedReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == WifiManager.NETWORK_STATE_CHANGED_ACTION) {

                    val connectivityManager =
                        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val caps =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (caps?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                        binding.imageViewWiFi.setImageResource(R.drawable.wifi_on)
                        if(!statusLoadImages)
                            fillImages()
                    } else {
                        binding.imageViewWiFi.setImageResource(R.drawable.wifi_off)
                    }

                }
            }
        }

        registerReceiver(networkChangedReceiver, IntentFilter(WifiManager.NETWORK_STATE_CHANGED_ACTION))
    }


    private fun fillImages() {
        putImage(binding.imageView1, "https://img.freepik.com/free-photo/fresh-yellow-daisy-single-flower-close-up-beauty-generated-by-ai_188544-15543.jpg?size=626&ext=jpg&ga=GA1.1.1292351815.1709510400&semt=ais")
        putImage(binding.imageView2, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtARWS5-gd7GuxJFL0IAdliUcc2_kIVA-20sWrECcRTxDBTZ8yTEuNG5APsVCX4JUToIA&usqp=CAU")
        putImage(binding.imageView3, "https://www.google.com/imgres?imgurl=https%3A%2F%2Fi.pinimg.com%2F236x%2F2a%2Ff5%2F3d%2F2af53d8f1be483dd0e05b7b18142c33c.jpg&tbnid=pK9MCRxQ3XgojM&vet=12ahUKEwjdpJfO6NyEAxXKoP0HHW7GBBkQMygAegQIARBx..i&imgrefurl=https%3A%2F%2Fwww.pinterest.com%2Fveralushnikova%2F%25D0%25BA%25D1%2580%25D0%25B0%25D1%2581%25D0%25B8%25D0%25B2%25D1%258B%25D0%25B5-%25D0%25BA%25D0%25B0%25D1%2580%25D1%2582%25D0%25B8%25D0%25BD%25D0%25BA%25D0%25B8%2F&docid=4YPQHS1FoH29pM&w=235&h=308&q=%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8&ved=2ahUKEwjdpJfO6NyEAxXKoP0HHW7GBBkQMygAegQIARBx")
        putImage(binding.imageView4, "https://i.pinimg.com/236x/93/ed/3a/93ed3af6411e1e8b997038c74c287a8a.jpg")
        statusLoadImages = false
    }
    private fun putImage(image: ImageView, url: String){
        Glide.with(this)
            .load(url)
            .into(image)
    }
}