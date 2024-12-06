package dao

import java.io.*


object FileUtil {
    @Throws(IOException::class)
    fun writeToFile(filename: String, data: String, append: Boolean = true) {
        val file = File(filename)
        file.parentFile?.mkdirs()
        BufferedWriter(FileWriter(file, append)).use { writer ->
            writer.write(data)
            if (append) {
                writer.newLine()
            }
        }
    }

    @Throws(IOException::class)
    fun readFromFile(filename: String): List<String> {
        val lines = mutableListOf<String>()
        val file = File(filename)
        if (!file.exists()) {
            return lines
        }
        BufferedReader(FileReader(file)).use { reader ->
            var line = reader.readLine()
            while (line != null) {
                lines.add(line)
                line = reader.readLine()
            }
        }
        return lines
    }
}
