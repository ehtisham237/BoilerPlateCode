package com.boilerplate.example.utils

import java.io.IOException

class ApiException(errorCode: Int, message: String) : IOException(message)

class NoInternetException(message: String) : IOException(message)