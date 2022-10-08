package ua.university.network.result

import ua.university.network.error.NetworkError

typealias NetworkResult<S> = Result<S, NetworkError>