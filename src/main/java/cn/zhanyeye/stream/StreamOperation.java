package cn.zhanyeye.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author zhanyeye
 * @Description filter(), map(), flatMap()
 * @Date 5/20/2021
 **/
public class StreamOperation {
    public static void main(String[] args) throws IOException {
        String contents =  new String(Files.readAllBytes(Paths.get("E:\\alice.txt")), StandardCharsets.UTF_8);
        List<String> words = List.of(contents.split("\\PL+"));

        // filter 返回一个新流，对原先的流进行过滤，满足谓词的元素被保留下来
        // filter 的参数是 Predicate<T>  （谓词）即从 T 到 boolean 的函数
        Stream<String> longWords = words.stream().filter(w -> w.length() > 12);

        // map 按照某种方式来转换流中的值
        Stream<String> lowercaseWords = words.stream().map(String::toLowerCase);
        Stream<String> firstLetters = words.stream().map(w -> w.substring(0, 1));

        // 得到了一个包含流的流
        Stream<Stream<String>> result = words.stream().map(w -> codePoints(w));
        // 为了将其平摊成当个流，可以使用 flatMap 方法而不是 map
        Stream<String> flatResult = words.stream().flatMap(w -> codePoints(w));

    }


    /**
     * 将字符串转换成字符串流
     * 这个方法可以正确的处理需要用两个字符来表示的 char 值
     * @param s
     * @return
     */
    public static Stream<String> codePoints(String s) {
        var result = new ArrayList<String>();
        int i = 0;
        while (i < s.length()) {
            int j = s.offsetByCodePoints(i, 1);
            result.add(s.substring(i, j));
            i = j;
        }
        return result.stream();
    }
}
