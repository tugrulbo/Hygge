package com.tugrulbo.hyggetb.util

interface Communicator {
        fun passDataCom(id:String)
        fun passMapData(brandName:String?,latitude:String?,longitude:String?)
}