package com.example.test
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.math.log

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private lateinit var url:String
    private lateinit var parent: Context
   inner class UserViewHolder : RecyclerView.ViewHolder {
        private lateinit var listUserView : TextView
        constructor(itemView: View): super(itemView){
            listUserView = itemView.findViewById(R.id.tv_user_item)
            itemView.setOnClickListener{
                var i = adapterPosition
                val repository = SearchLoginProvider.provideSearchLogin()
                repository.searchLogin(users[i].login)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe ({
                            result ->
                        var secAct = SecondActivity::class.java
                        var secActIntent:Intent =Intent(parent,secAct)
                        secActIntent.putExtra("login",result.login)
                        secActIntent.putExtra("name",result.name)
                        secActIntent.putExtra("public_repos",result.public_repos)
                        secActIntent.putExtra("followers",result.followers)
                        secActIntent.putExtra("following",result.following)
                        parent.startActivity(secActIntent)
                    }, { error ->
                        error.printStackTrace()
                    })
           //     GitInfoQueryTask().execute(URL(users[i].url))



            }
        }
        fun bind (userlogin: String) {
            listUserView.text = userlogin
        }
    }

    private var users = mutableListOf<UserGit>()
    constructor(users : MutableList<UserGit>, parent: Context){
       this.users = users
        this.parent=parent
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var context : Context = parent.context;
       var layoutIdForListItem:Int = R.layout.user_list_item
       var inflater: LayoutInflater = LayoutInflater.from(context)
        var view:View = inflater.inflate(layoutIdForListItem,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position].login)
    }
}