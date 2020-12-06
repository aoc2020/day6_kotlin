@file:JvmName("Main")

package com.github.aoc2020.day6_kotlin

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun main() {
    val input = readFile("input.txt")
    val groups = splitPassports(input);
    val answer1: Long = groups.map { x -> anyYes(x) }.reduce { x, y -> x + y };
    val answer2: Int = groups.map { x -> allYes(x) }.reduce { x, y -> x + y };
    println("Answer 1 : $answer1");
    println("Answer 1 : $answer2");
    var id2 = "FBFBBFFRLR";
    println("seat id: $id2");
}

fun allYes(l: List<String>): Int {
    var acc = 0;
    for (n in 'a'..'z') {
        val con = l.filter { s -> s.contains(n) }
        if (con.size == l.size) acc+=1
    }
    return acc
}

fun anyYes(l: List<String>): Long {
    var s: String = l.reduce { a,b -> a+b}
    return s.chars().distinct().count()
}

fun splitPassports(lines: List<String>): List<List<String>> {
    var result = ArrayList<ArrayList<String>>();
    var current = ArrayList<String>();
    for (s in lines) {
        if ("".equals(s)) {
            result.add(current);
            current = ArrayList<String>();
        } else {
            current.add(s);
        }
    }
    result.add(current)
    return result;
}

fun readFile(fileName: String): List<String> {
    return Files.lines(Path.of(fileName))
        .collect(Collectors.toUnmodifiableList())
}