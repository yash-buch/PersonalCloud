package com.binc.personalcloud.core.util

import com.binc.personalcloud.core.entity.ILogger

class ConsoleLogger: ILogger {
    override fun info(tag: String, message: String?) {
        println("i> $tag: $message")
    }

    override fun debug(tag: String, message: String?) {
        println("d> $tag: $message")
    }

    override fun warning(tag: String, message: String?) {
        println("w> $tag: $message")
    }

    override fun error(tag: String, message: String?) {
        println("e> $tag: $message")
    }

}