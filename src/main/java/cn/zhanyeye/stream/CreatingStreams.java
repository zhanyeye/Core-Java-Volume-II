package cn.zhanyeye.stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author zhanyeye
 * @Description 流的创建
 * @Date 5/19/2021
 **/
public class CreatingStreams {

    /**
     * 打印 stream 的部分数据
     * @param title
     * @param stream
     * @param <T>
     */
    public static<T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());

        System.out.print(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:\\alice.txt");
        var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        // 如果有一个数组，可以通过静态的 Stream.of() 方法创建 stream
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);

        // Stream.of() 方法具有可变长度的参数
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);

        // 创建一个不包含任何元素的流
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        // Arrays.stream(arr, from, to) 使用数组的一部分元素去创建一个流
        Stream<String> partOfWord = Arrays.stream(contents.split("\\PL+"), 0, 2);
        show("partOfWord", partOfWord);

        // Stream 接口有2个创建无线流的静态方法： generate(), iterate()
        // 获得一个常量值的流
        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        // 获得一个随机数流
        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        // 产生 0, 1, 2, 3, ... 这样的序列
        // iterate 的第一个参数是种子，第二个参数是函数，反复的将该函数应用到之前的结果上
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        // 如果要产生一个有限序列，则需要添加一个谓词来描述迭代应该如何结束, 只要谓词拒绝了某个迭代生产的值，这个流即结束
        BigInteger limit = new BigInteger("10000000");
        Stream<BigInteger> limitIntegers = Stream.iterate(BigInteger.ZERO, n -> n.compareTo(limit) < 0, n -> n.add(BigInteger.ONE));
        show("limitIntegers", limitIntegers);

        // 若对象为null 流的长度为0，否则流的长度为1，与flatMap 相结合使用
        Stream<String> emptyable = Stream.ofNullable(null);
        show("emptyable", emptyable);
    }

}
