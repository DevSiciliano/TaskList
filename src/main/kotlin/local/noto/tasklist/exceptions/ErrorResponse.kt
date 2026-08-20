package local.noto.tasklist.exceptions

import java.time.Instant

class ErrorResponse(
    val statusCode: Int,
    val message: String,
    val fieldErrors: Map<String, String> = emptyMap(),
    val timeStamp: Instant = Instant.now()
)