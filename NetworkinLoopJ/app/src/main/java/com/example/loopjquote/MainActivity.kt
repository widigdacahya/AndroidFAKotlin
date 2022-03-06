package com.example.loopjquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.loopjquote.databinding.ActivityMainBinding
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.HttpGet
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import cz.msebera.android.httpclient.conn.scheme.Scheme
import cz.msebera.android.httpclient.conn.ssl.SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
import org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        getRandomQuote()
    }

    private fun getRandomQuote() {
        mainBinding.progressbarUI.visibility = View.VISIBLE
        val client = AsyncHttpClient()


  




        val url = "https://quote-api.dicoding.dev/random"




        client.get(url,object: AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>,responseBody: ByteArray) {

                mainBinding.progressbarUI.visibility = View.INVISIBLE

                val result = String(responseBody)
                Log.d(TAG,result)
                try{
                    val responseObject = JSONObject(result)

                    val quote = responseObject.getString("en")
                    val author = responseObject.getString("author")

                    mainBinding.tvQuoteUI.text = quote
                    mainBinding.tvAuthorUI.text = author
                } catch (e:Exception) {
                    Toast.makeText(this@MainActivity,e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }


            override fun onFailure(statusCode: Int,headers: Array<Header>,responseBody: ByteArray, error: Throwable) {
                mainBinding.progressbarUI.visibility = View.INVISIBLE

                val errorMessage = when(statusCode) {
                    401 -> "$statusCode : Bad Req"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode \n ${error.message}"
                }
                Toast.makeText(this@MainActivity,errorMessage,Toast.LENGTH_LONG).show()
            }

        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}