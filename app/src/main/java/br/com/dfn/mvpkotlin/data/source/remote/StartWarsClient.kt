package br.com.dfn.mvpkotlin.data.source.remote

object StartWarsClient {
    private var mService: ServiceClient<StarWarsApi>? = null
    private var mClient: StarWarsApi? = null

    init {
        mService = ServiceClient("http://swapi.co/api/")
        mClient = mService!!.getServiceApi(StarWarsApi::class.java)
    }

    fun getApi(): StarWarsApi {
        return mClient!!
    }

}