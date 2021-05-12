package me.jerry.example.graphql.connection

import graphql.relay.ConnectionCursor
import graphql.relay.DefaultConnectionCursor
import graphql.relay.Edge
import org.springframework.stereotype.Component
import java.util.*

@Component
class CursorUtil {

    fun from(id: UUID): ConnectionCursor {
        return DefaultConnectionCursor(Base64.getEncoder().encodeToString(id.toString().toByteArray()))
    }

    fun createCursorWith(id: UUID): ConnectionCursor? {
        return DefaultConnectionCursor(
            Base64.getEncoder().encodeToString(id.toString().toByteArray())
        )
    }

    fun decode(cursor: String): UUID {
        return UUID.fromString(String(Base64.getDecoder().decode(cursor)))
    }

    fun <T> getFirstCursorFrom(edges: List<Edge<T>>): ConnectionCursor? {
        return if (edges.isEmpty()) null else edges[0].cursor
    }

    fun <T> getLastCursorFrom(edges: List<Edge<T>>): ConnectionCursor? {
        return if (edges.isEmpty()) null else edges[edges.size - 1].cursor
    }

}
