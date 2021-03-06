package es.guillermoorellana.keynotedex.backend.data.conferences

import org.jetbrains.squash.results.*

fun transformConference(it: ResultRow): Conference =
    Conference(
        name = it[ConferencesTable.name]
    )

fun Conference.toDto() = es.guillermoorellana.keynotedex.dto.Conference(
    name = name
)
