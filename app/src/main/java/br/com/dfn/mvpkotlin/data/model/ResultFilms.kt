package br.com.dfn.starwarskotlin.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diegonascimento on 18/12/17.
 */
class ResultFilms {
    @SerializedName("results")
    var results:List<Film> = ArrayList()
}