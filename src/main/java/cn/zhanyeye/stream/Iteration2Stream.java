package cn.zhanyeye.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Iteration2Stream {
    public static void main(String[] args) {
        String contents = null;
        try {
            contents = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> words = List.of(contents.split("\\PL+"));
    }
}
