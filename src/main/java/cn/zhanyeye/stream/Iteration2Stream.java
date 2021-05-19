package cn.zhanyeye.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Iteration2Stream {
    public static void main(String[] args) throws IOException {
        String contents =  new String(Files.readAllBytes(Paths.get("E:\\alice.txt")), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));
        useIteration(words);
        useStream(words);
        useParallelStream(words);
    }

    /**
     * 使用迭代来计数
     * @param words
     */
    public static void useIteration(List<String> words) {
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) {
                count++;
            }
        }
        System.out.println("userIteration: " + count);
    }

    /**
     * 使用 stream 计数
     * @param words
     */
    public static void useStream(List<String> words) {
        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println("useStream: " + count);
    }

    /**
     * 使用 parallelStream 计数
     * @param words
     */
    public static void useParallelStream(List<String> words) {
        long count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println("useParallelStream: " + count);
    }

}
