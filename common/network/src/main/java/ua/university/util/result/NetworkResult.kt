package ua.university.util.result

import ua.university.util.error.NetworkError

typealias NetworkResult<S> = Result<S, NetworkError>