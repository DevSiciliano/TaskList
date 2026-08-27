package local.noto.tasklist.dtos.mapper

import local.noto.tasklist.dtos.UserSummaryDto
import local.noto.tasklist.models.User

fun User.toSummaryDto(): UserSummaryDto =
    UserSummaryDto(
        id = requireNotNull(id) { "ID must not be null" },
        username = username
    )
