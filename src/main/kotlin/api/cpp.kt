import java.io.*
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

fun runJavaCode(javaCode: String): String {
    // Extract the class name from the provided Java code
    val className = extractClassName(javaCode)

    if (className.isEmpty()) {
        return "Error: Could not determine class name."
    }

    // Create a temporary directory to store the .java file and compiled .class file
    val tempDir = File(System.getProperty("java.io.tmpdir"))
    val javaFile = File(tempDir, "$className.java")
    val classFile = File(tempDir, "$className.class")

    try {
        // Write the Java code to the newly created .java file
        BufferedWriter(FileWriter(javaFile)).use { writer ->
            writer.write(javaCode)
        }

        // Command to compile the Java code
        val compileCommand = "javac ${javaFile.absolutePath}"

        // Run the compile command
        val compileProcess = Runtime.getRuntime().exec(compileCommand)
        val compileSuccess = compileProcess.waitFor(10, TimeUnit.SECONDS)

        // If compilation fails, return the error output
        if (!compileSuccess || compileProcess.exitValue() != 0) {
            return "Compilation Error: ${readInputStream(compileProcess.errorStream)}"
        }

        // Run the compiled Java class using `java`
        val runCommand = "java -cp ${tempDir.absolutePath} $className"
        val runProcess = Runtime.getRuntime().exec(runCommand)

        // Capture the output and error from running the Java program
        val output = readInputStream(runProcess.inputStream)
        val errorOutput = readInputStream(runProcess.errorStream)

        // Clean up the .java and .class files after execution
        javaFile.delete()
        classFile.delete()

        // Return the output of the execution or any errors
        return if (errorOutput.isEmpty()) {
            output
        } else {
            "Execution Error: $errorOutput"
        }

    } catch (e: IOException) {
        return "Error: ${e.message}"
    }
}

// Helper function to extract the class name from the Java code
private fun extractClassName(javaCode: String): String {
    // Regex to extract the class name from the Java code
    val pattern = Pattern.compile("public class (\\w+)")
    val matcher = pattern.matcher(javaCode)

    return if (matcher.find()) {
        matcher.group(1)  // Return the class name found in the Java code
    } else {
        ""  // Return an empty string if the class name isn't found
    }
}

// Helper function to read the InputStream (to capture output or error)
private fun readInputStream(inputStream: InputStream): String {
    val reader = BufferedReader(InputStreamReader(inputStream))
    val output = StringBuilder()
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        output.append(line).append("\n")
    }
    return output.toString()
}
