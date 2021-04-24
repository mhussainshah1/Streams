package stream.intermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateOperation {
    public static void main(String[] args) {
        //filter()
        Stream.of("monkey", "gorilla", "bonobo")
                .filter(x -> x.startsWith("m"))
                .forEach(System.out::print); // monkey

        //distinct
        Stream.of("duck", "duck", "duck", "goose")
                .distinct()
                .forEach(System.out::print); // duckgoose

        //limit() and skip()
        Stream.iterate(1, n -> n + 1)
                .skip(5)
                .limit(2)
                .forEach(System.out::println); // 67

        //map()
        Stream.of("monkey", "gorilla", "bonobo")
                .map(String::length)
                .forEach(System.out::println); // 676

        //flatMap()
        List<String> zero = Arrays.asList();
        var one = Arrays.asList("Bonobo");
        var two = Arrays.asList("Mama Gorilla", "Baby Gorilla");

        Stream.of(zero, one, two)
                .flatMap(List::stream)
                .forEach(System.out::println);

        //sorted()
        Stream.of("brown-", "bear-")
                .sorted()
                .forEach(System.out::print); // bear-brown-

        Stream.of("brown bear-", "grizzly-")
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::print); // grizzly-brown bear-

//       Stream.of("brown bear-", "grizzly-")
//              .sorted(Comparator::reverseOrder); // DOES NOT COMPILE

        //peek()
        long count = Stream.of("black bear", "brown bear", "grizzly")
                .filter(s -> s.startsWith("g"))
                .peek(System.out::println)
                .count(); // grizzly
        System.out.println(count); // 1

        //without peek
        var numbers = new ArrayList<>();
        var letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');

        Stream.of(numbers, letters)
                .map(List::size)
                .forEach(System.out::print); // 11
        System.out.println(numbers + " " + letters);

        //Bad Peek - update variable and result of stream
        Stream.of(numbers, letters)
                .peek(x -> x.remove(0))
                .map(List::size)
                .forEach(System.out::print); // 00
        System.out.println(numbers + " " + letters);
    }
}
