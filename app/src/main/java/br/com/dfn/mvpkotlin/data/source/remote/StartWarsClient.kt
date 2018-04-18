package br.com.dfn.mvpkotlin.data.source.remote

object StartWarsClient {
    private val mService: ServiceClient<StarWarsApi> = ServiceClient("http://swapi.co/api/")
    private var mClient: StarWarsApi

    init {
        mClient = mService.getServiceApi(StarWarsApi::class.java)
    }

    fun getApi(): StarWarsApi {
        return mClient
    }
}