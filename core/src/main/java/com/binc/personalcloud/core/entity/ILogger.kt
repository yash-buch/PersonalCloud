package com.binc.personalcloud.core.entity

interface ILogger {
    fun info(tag: String, message: String?)
    fun debug(tag: String, message: String?)
    fun warning(tag: String, message: String?)
    fun error(tag: String, message: String?)
}