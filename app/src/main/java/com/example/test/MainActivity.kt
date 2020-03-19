package com.example.test
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var searchField: EditText
    private lateinit var searchButton: Button
    private lateinit var userList : RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private var users = mutableListOf<User>()
   inner class GitQueryTask : AsyncTask<URL,Unit, String>() {
        override fun doInBackground(vararg params: URL?): String {
            var res:String? = null
            try {
                res = Network.getResponseFromUrl(params[0])


            }catch ( e : IOException){
                e.printStackTrace()
            }
            return res.toString()
        }

        override fun onPostExecute(result: String?) {
           var jsonRes = JSONObject(result)
            var jsonAR : JSONArray = jsonRes.getJSONArray("items")
           var len = jsonAR.length()
            for(user in Array(jsonAR.length()) {i ->jsonAR.getJSONObject(i)} ){
                users.add(User(user.getString("login"),user.getString("avatar_url"),user.getString("url")))
            }
            usersAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchField = findViewById(R.id.search_field)
        searchButton = findViewById(R.id.search_button)
        userList = findViewById(R.id.rv_users)
        var layoutManager =  LinearLayoutManager(this)
        userList.layoutManager = layoutManager

        usersAdapter = UsersAdapter(users, this)
        userList.adapter = usersAdapter
    }
     fun onClick(view: View){
         users.clear()
       var user = searchField.getText().toString()
         GitQueryTask().execute(URL("https://api.github.com/search/users?q=$user+in"))
    }

}
