package tasks

import java.io.File
import java.util.regex.Pattern

fun main() {
    val dir = "/Users/chaofan.zhang/AndroidStudioProjects/alias-android"

    File(dir)
        .walkTopDown()
        .filter { it.isDirectory }
        .forEach { dir ->
            val gradleFiles = dir.listFiles { file: File ->
                file.name.endsWith(".gradle") && file.name.length > ".gradle".length
            }
            if (gradleFiles == null || gradleFiles.isEmpty()) return@forEach

            println("${dir.name}")
            if (dir.name == "loading") {
                println("zcf")
            }

            val gradleFile = gradleFiles[0]
            // 1. add namespace
//            addNamespace(dir, gradleFile)

            // 2. replace Kapt with Ksp
//            replaceKaptWithKsp(gradleFile)

            // 3. replace kotlinCompilerExtensionVersion
//            replaceKotlinCompilerExtensionVersion(gradleFile)

            // use kapt for epoxy compiler
//            useKaptForEpoxyCompiler(gradleFile)

            // delete AndroidManifest.xml for android library
            deleteManifestFile(dir, gradleFile)

            // use same Namespace -- doesn't work
//            useSameNamespace(dir, gradleFile)
        }
}

fun useKaptForEpoxyCompiler(gradleFile: File) {
    val lines = gradleFile.readLines()
    val newLines = lines.toMutableList()

    val kspLineIndex = lines.indexOfFirst { it.contains("ksp deps.epoxy.compiler") }
    if (kspLineIndex != -1) {
        newLines[kspLineIndex] = "  kapt deps.epoxy.compiler"

        val kspPluginIndex = lines.indexOfFirst { it.contains("id 'com.google.devtools.ksp'") }
        newLines.add(kspPluginIndex, "  id 'org.jetbrains.kotlin.kapt'")

        gradleFile.writeText(newLines.joinToString("\n"))
    }
}

fun useSameNamespace(directory: File, gradleFile: File) {
    val lines = gradleFile.readLines()
    val newLines = lines.toMutableList()
    if (lines.any { it.contains("com.android.library") || it.contains("java-library") }) {
        val manifestFile = File(directory, "src/main/AndroidManifest.xml")
        if (manifestFile.exists()) {
            manifestFile.delete()
        }
        val namespaceLineIndex = lines.indexOfFirst { it.contains("namespace ") }
        if (namespaceLineIndex != -1) {
            newLines[namespaceLineIndex] = "  namespace 'com.onesell.android'"
            gradleFile.writeText(newLines.joinToString("\n"))
        }
    }
}

fun deleteManifestFile(directory: File, gradleFile: File) {
    val lines = gradleFile.readLines()
    val newLines = lines.toMutableList()
    if (lines.any { it.contains("com.android.library") || it.contains("java-library") }) {
        val manifestFile = File(directory, "src/main/AndroidManifest.xml")
        if (manifestFile.exists()) {
            val manifestLines = manifestFile.readLines()
            if (manifestLines.size > 3) {
                println("zcf large manifestFile, ${manifestFile.absolutePath}")
            } else {
                manifestFile.delete()
            }
        }
    }
}

fun replaceKotlinCompilerExtensionVersion(gradleFile: File) {
    val lines = gradleFile.readLines()
    val newLines = lines.toMutableList()

    val versionIndex = lines.indexOfFirst { it.contains("kotlinCompilerExtensionVersion versions.compose") }

    if (versionIndex != -1) {
        newLines[versionIndex] = "    kotlinCompilerExtensionVersion versions.kotlinCompilerExtensionVersion"
        gradleFile.writeText(newLines.joinToString("\n"))
    }
}

fun replaceKaptWithKsp(gradleFile: File) {
    val lines = gradleFile.readLines()
    val newLines = lines.toMutableList()

    val kaptIndex = lines.indexOfFirst { it.contains("org.jetbrains.kotlin.kapt") }

    if (kaptIndex != -1) {
        newLines[kaptIndex] = "  id 'com.google.devtools.ksp'"
        val dependencyBlockLineIndex = lines.indexOfFirst { it.contains("dependencies {") || it.contains("dependencies{") }

        fun hasKaptUsage() = lines.filterIndexed { index, _ -> index > dependencyBlockLineIndex }.any { it.contains("kapt ") }
        if (dependencyBlockLineIndex > 0 && hasKaptUsage()) {
            newLines.forEachIndexed { index, s ->
                if (index > dependencyBlockLineIndex) {
                    newLines[index] = s.replace("kapt ", "ksp ")
                }
            }
        } else {
            newLines.removeAt(kaptIndex)
        }

        gradleFile.writeText(newLines.joinToString("\n"))
    }
}

fun addNamespace(directory: File, gradleFile: File) {
    val startSearch = "package=\""
    val endSearch = "\""
    val packagePattern = Pattern.compile("[\\S\\s]*$startSearch(.*)$endSearch[\\S\\s]*")

    val lines = gradleFile.readLines()
    if (lines.any { it.contains("com.android.library") }) {
        val manifestFile = File(directory, "src/main/AndroidManifest.xml")
        if (manifestFile.exists()) {
            val manifestContent = manifestFile.readText()
            val matcher = packagePattern.matcher(manifestContent)
            if (matcher.matches()) {
                val packageName = matcher.group(1)
                println("    package: $packageName")
                addNamespaceInGradleFile(gradleFile, packageName)
            }
        }
    }
}

fun addNamespaceInGradleFile(gradleFile: File, packageName: String) {
    val lines = gradleFile.readLines()
    val newLines = lines.toMutableList()

    val androidBlockLineIndex = lines.indexOfFirst { it.contains("android {") || it.contains("android{") }
    if (androidBlockLineIndex != -1) {
        newLines.add(androidBlockLineIndex + 1, "  namespace \"$packageName\"\n")
    } else {
        val dependencyBlockLineIndex = lines.indexOfFirst { it.contains("dependencies {") || it.contains("dependencies{") }
        val insertLine = if (dependencyBlockLineIndex != -1) {
            dependencyBlockLineIndex
        } else {
            lines.size
        }
        newLines.add(insertLine, "android {\n  namespace \"$packageName\"\n}\n")
    }

    gradleFile.writeText(newLines.joinToString("\n"))
}