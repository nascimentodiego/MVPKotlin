package br.com.dfn.starwarskotlin.core.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diegonascimento on 18/12/17.
 */
class Character(@SerializedName("name") var name: String, @SerializedName("gender") var gender: String) {
}