package com.example.test
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var searchField: EditText
    private lateinit var searchButton: Button
    private lateinit var userList : RecyclerView
    private lateinit var usersAdapter: UsersAdapter
    private var users = mutableListOf<UserGit>()

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
     fun onClick(view: View,page:Int){

         users.clear()
       var user = searchField.getText().toString()
         val repository = SearchRepositoryProvider.provideSearchRepository()
         repository.searchUsers(user, page)
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeOn(Schedulers.io())
             .subscribe ({
                     result ->
                 users.addAll(result.items)
                 usersAdapter.notifyDataSetChanged()
             }, { error ->
                 error.printStackTrace()
             })

    }

}
