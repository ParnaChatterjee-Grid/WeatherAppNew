package com.example.common

sealed class CustomExceptions(message: String) : Exception(message) {

    class BadRequestException(cause: Throwable) : CustomExceptions(cause.message?: " Data not found ")
    class UnauthorizedException(cause: Throwable) : CustomExceptions(cause.message ?: " Unauthorized error found")
    class ServerErrorException(cause: Throwable) : CustomExceptions(cause.message ?: "ServerError found")
    class UnknownNetworkException(cause: Throwable) : CustomExceptions(cause.message ?: "UnknownNetworkException found")
    class TimeoutException(cause: Throwable) : CustomExceptions(cause.message?: " TimeoutException found ")
    class UnknownException(cause: Throwable) : CustomExceptions(cause.message ?: "Unknown Exception found")

}