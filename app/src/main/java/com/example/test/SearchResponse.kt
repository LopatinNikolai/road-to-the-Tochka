package com.example.test

data class UserGit(
    val login: String,
    val id: Long,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val starred_url: String,
    val gists_url: String,
    val type: String,
    val score: Int
)

data class Result (val total_count: Int, val incomplete_results: Boolean, val items: MutableList<UserGit>)
data class UserInfo (
    val login: String,
    val id: Long,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url : String,
    val starred_url:String,
    var subscriptions_url: String,
    var organizations_url: String,
    var repos_url: String,
    var eventsUrl: String,
    var receivedEvents_url: String,
    var type: String,
    var siteAdmin: Boolean,
    var name: String?,
    var company: Any?,
    var blog: String?,
    var location: Any?,
    var email: Any?,
    var hireable: Any?,
    var bio: Any?,
    var public_repos: String?,
    var public_gists: Long?,
    var followers: String?,
    var following: String?,
    var createdAt: String?,
    var updatedAt: String?
)