package br.com.dfn.mvpkotlin.data.source.remote

import br.com.dfn.mvpkotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import br.com.dfn.mvpkotlin.util.Constants

class ServiceClient<T>(private var mBaseUrl:String) {
    private var mOkHttpclient: OkHttpClient? = null
    private var mRetrofit: Retrofit? = null

    private fun buildOkHttp() {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        mOkHttpclient = OkHttpClient.Builder()
                .connectTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build()
    }

    private fun buildRetrofit() {
        mRetrofit = Retrofit.Builder()
                .addConverterFactory(nullOnEmptyConverterFactory)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(mBaseUrl!!)
                .client(mOkHttpclient!!)
                .build()

    }

    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
            val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
            override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
        }
    }

    fun getServiceApi(api: Class<T>): T {

        if (mOkHttpclient == null) {
            buildOkHttp()
        }

        if (mRetrofit == null) {
            buildRetrofit()
        }

        return mRetrofit!!.create<T>(api)
    }
}