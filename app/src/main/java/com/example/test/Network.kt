package com.example.test

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class Network {
    companion object{
        public fun getResponseFromUrl (url: URL?): String? {
            var urlConnection = url?.openConnection() as HttpURLConnection
            try {
                var inp = urlConnection.inputStream
                var scanner = Scanner(inp)
                scanner.useDelimiter("\\A")
                var hasInput : Boolean = scanner.hasNext()
                if (hasInput){
                    return scanner.next()
                }else{
                    return null
                }
            }finally {
                urlConnection.disconnect()
            }


        }
    }

}