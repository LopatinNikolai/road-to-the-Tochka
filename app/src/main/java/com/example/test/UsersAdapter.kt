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
                GitInfoQueryTask().execute(URL(users[i].url))



            }
        }
        fun bind (userlogin: String) {
            listUserView.text = userlogin
        }
    }
   inner class GitInfoQueryTask : AsyncTask<URL, Unit, String>() {
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
            var secAct = SecondActivity::class.java
            var secActIntent:Intent =Intent(parent,secAct)
            secActIntent.putExtra("login",jsonRes.getString("login"))
            secActIntent.putExtra("name",jsonRes.getString("name"))
            secActIntent.putExtra("public_repos",jsonRes.getString("public_repos"))
            secActIntent.putExtra("followers",jsonRes.getString("followers"))
            secActIntent.putExtra("following",jsonRes.getString("following"))
            parent.startActivity(secActIntent)
        }
    }

    private var users = mutableListOf<User>()
    constructor(users : MutableList<User>, parent: Context){
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