package com.prolearn.coroutinesjetpackcompose.model;

data class SportsModel (
    val name :String,
    val description:String,
    val players:Int,
    val bowlers:Int,
    val batsman:Int
)

data class CricketResponse(
    val data : String
);
