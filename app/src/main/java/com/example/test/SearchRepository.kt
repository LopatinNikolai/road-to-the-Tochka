package com.example.test

import io.reactivex.Observable


class SearchRepository(val apiService: GithubApiService) {
    fun searchUsers(name: String, page:Int): Observable<Result> {
        return apiService.search(query = "$name",page = page,perPage = 30)
    }
}
object SearchRepositoryProvider {
    fun provideSearchRepository(): SearchRepository {
        return SearchRepository( GithubApiService.create())
    }
}
class SearchLogin(val apiService: GithubApiService) {
    fun searchLogin(login: String): Observable<UserInfo> {
        return apiService.searchLogin(login)
    }
}
object SearchLoginProvider {
    fun provideSearchLogin(): SearchLogin {
        return SearchLogin( GithubApiService.create())
    }
}
//query = "$name",page = page,perPage = 30