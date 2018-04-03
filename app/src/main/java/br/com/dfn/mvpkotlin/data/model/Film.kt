package br.com.dfn.starwarskotlin.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diegonascimento on 18/12/17.
 */
class Film(title: String, description: String) {

    var mId: Int? = null

    @SerializedName("title")
    var title: String = title

    @SerializedName("description")
    var description: String = description

    @SerializedName("characters")
    var characters: List<String> = ArrayList()
    var mCharacters: MutableList<Character> = mutableListOf()

}