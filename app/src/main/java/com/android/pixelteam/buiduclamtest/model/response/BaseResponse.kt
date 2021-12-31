package com.android.pixelteam.buiduclamtest.model.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> (@SerializedName("data") val data : T)