package es.guillermoorellana.keynotedex.backend.dao.users

import org.jetbrains.squash.results.ResultRow
import org.jetbrains.squash.results.get
import es.guillermoorellana.keynotedex.dto.User as DtoUser

fun transformUser(it: ResultRow): User =
    User(
        userId = it[UsersTable.id],
        email = it[UsersTable.email],
        displayName = it[UsersTable.displayName],
        passwordHash = it[UsersTable.passwordHash],
        bio = it[UsersTable.bio]
    )

fun User.toDto() =
    DtoUser(
        userId = userId,
        displayName = displayName,
        bio = bio
    )
