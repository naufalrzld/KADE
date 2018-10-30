package com.naufalrzld.footballmatchschedule.service

class RetrofitService {
    companion object {
        fun sendRequest() : Services? = BaseService.getAPIClient()?.create(Services::class.java)
    }
}