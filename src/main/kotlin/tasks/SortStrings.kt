package tasks

import okio.BufferedSink
import okio.buffer
import okio.sink
import java.io.File

fun main() {
    println("test")

    val fileName = "src/main/resources/strings.xml"
    val exportFileName = "src/main/resources/strings-export.xml"

    val lines: List<String> = File(fileName).readLines()
    val sink = File(exportFileName).sink()
    val bufferedSink = sink.buffer()

    val linesMap = mutableMapOf<String, String>()

    lines.forEach { line ->
        println(line)
        if (line.isBlank()) return@forEach

        val name = findStringNodeName(line)
        if (name == null || line.isExceptional("plurals name=\"", "item quantity=\"")) {
            exportLine(line, bufferedSink)
        }
        if (name != null) {
            linesMap.put(name, line)
        }
    }

    val sortedMap = linesMap.toSortedMap(object : Comparator<String> {
        override fun compare(o1: String, o2: String): Int {
            return o1.lowercase().compareTo(o2.lowercase())
        }
    })

    sortedMap.entries.forEach { (name, line) ->
        exportLine(line, bufferedSink)
    }

    bufferedSink.flush()
    sink.close()
}

fun findStringNodeName(line: String): String? {
    val start = line.indexOf("name=\"")
    if (start == -1) return null
    val nameStart = start + 6
    val nameEnd = line.indexOf("\"", startIndex = nameStart)
    return line.substring(nameStart, nameEnd)
}

fun String.isExceptional(vararg exceptionalKeys: String): Boolean {
    return exceptionalKeys.any { this.indexOf(it, ignoreCase = false) > -1 }
}

fun exportLine(line: String, bufferedSink: BufferedSink) {
    bufferedSink.writeUtf8(line)
    bufferedSink.writeUtf8("\n")
}