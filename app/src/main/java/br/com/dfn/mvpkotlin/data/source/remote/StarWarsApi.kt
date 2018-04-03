package br.com.dfn.mvpkotlin.data.source.remote

import br.com.dfn.starwarskotlin.core.model.ResultFilms
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {
    @GET("films")
    fun getFilms(): Observable<ResultFilms>

    @GET("people/{id}")
    fun getCharacter(@Path("id") id: String): Observable<Character>
}