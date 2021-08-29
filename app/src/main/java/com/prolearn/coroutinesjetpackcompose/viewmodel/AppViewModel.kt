package com.prolearn.coroutinesjetpackcompose.viewmodel;

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject

import com.prolearn.coroutinesjetpackcompose.model.CricketResponse

import com.prolearn.coroutinesjetpackcompose.model.SportsModel
import com.prolearn.coroutinesjetpackcompose.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException


class AppViewModel : ViewModel {

    private val appRepository : AppRepository;
    constructor(){
        Log.d("appdevelopment","AppViewModel constructor");

    }
    val footballState : MutableState<SportsModel> = mutableStateOf( SportsModel("","",0,0,0));

    init{
        appRepository = AppRepository();
        Log.d("appdevelopment","AppViewModel init");
        viewModelScope.launch(Dispatchers.IO) {
            val footballdata =  getFootball("12");

            Log.d("appdevelopment",footballdata.toString());
            footballState.value=footballdata;
        }

        viewModelScope.launch(Dispatchers.IO) {

            try{
                val cricketdata =  getCricket();
                Log.d("appdevelopment",cricketdata.toString());
            }
            catch(httpException : HttpException){

                Log.d("appdevelopment","getCricket httpException");

                Log.d("appdevelopment",""+httpException.code());
                Log.d("appdevelopment",""+httpException.message());
                Log.d("appdevelopment",""+httpException.response());

                val errorBody: ResponseBody? =  httpException.response()?.errorBody()

                val adapter = Gson().getAdapter(CricketResponse::class.java)
                val errorParser = adapter.fromJson(errorBody?.string())

                Log.d("appdevelopment",errorParser.toString());
                Log.d("appdevelopment",errorParser.data);

            }
            catch(e : Exception){
                Log.d("appdevelopment","getCricket exception");
                Log.d("appdevelopment",e.toString());
                val e_message = e.message.orEmpty()
                Log.d("appdevelopment",e_message)

                e.printStackTrace()
            }
        }
    }
    suspend fun getFootball(pid : String) : SportsModel {
        Log.d("appdevelopment","AppViewModel getFootball");
        return appRepository.getFootball(pid);
    }

    suspend fun getCricket() : CricketResponse {
        return appRepository.getCricket();
    }
}