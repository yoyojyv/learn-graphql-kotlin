package me.jerry.example.graphql.support

import org.slf4j.LoggerFactory

abstract class Logger {
    val logger = LoggerFactory.getLogger(this.javaClass)
}
