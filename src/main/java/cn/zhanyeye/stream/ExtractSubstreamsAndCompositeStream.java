package cn.zhanyeye.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @Author zhanyeye
 * @Description
 * @Date 5/20/2021
 **/
public class ExtractSubstreamsAndCompositeStream {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("E:\\alice.txt");
        var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        // limit(n) 返回一个新的流，在 n 个元素之后结束，（若比 n 短，则会在该流结束的时候结束）
        // 对于裁剪无限流的尺寸特别有用
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);

        // 产生一个流，除去前 n 个元素之外的所有元素
        Stream<String> words = Stream.of(contents.split("\\PL+")).skip(1);

        // 产生一个流，它的元素是当前流中所有满足谓词条件的元素
        Stream<String> initialDigits = StreamOperation.codePoints(contents).takeWhile(s -> "0123456789".contains(s));

        // dropWhile 会在条件为真的时候丢弃元素
        Stream<String> withoutInitialWhiteSpace = StreamOperation.codePoints(contents).dropWhile(s -> s.trim().length() == 0);

        // Stream 类的静态方法 concat() 将两个流链接起来
        // 第一个流不应该是无限的，否则第二流永远也没有机会处理
        Stream<String> combined = Stream.concat(StreamOperation.codePoints("Hwllo"), StreamOperation.codePoints("World"));
    }
}
