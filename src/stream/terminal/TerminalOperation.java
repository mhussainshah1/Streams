package stream.terminal;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperation {

    public static void main(String[] args) {
        //count()
        long count = Stream
                .of("monkey", "gorilla", "bonobo")
                .count();
        System.out.println("count");

        //min() and max()
        Stream.of("monkey", "ape", "bonobo")
                .min((s1, s2) -> s1.length() - s2.length())
                .ifPresent(System.out::println); // ape

        boolean present = Stream.empty()
                .min((s1, s2) -> 0)
                .isPresent();
        System.out.println(present); // false

        //findAny() and findFirst()
        Stream.of("monkey", "gorilla", "bonobo")
                .findAny()
                .ifPresent(System.out::println);

        Stream.generate(() -> "chimp")
                .findAny()
                .ifPresent(System.out::println); // chimp

        //allMatch(), anyMatch() and noneMatch()
        var list = List.of("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

        System.out.println(list.stream().anyMatch(pred)); // true
        System.out.println(list.stream().allMatch(pred)); // false
        System.out.println(list.stream().noneMatch(pred)); // false
        System.out.println(infinite.anyMatch(pred)); // true

        infinite.forEach(System.out::println);

        //forEach()
        Stream.of("Monkey", "Gorilla", "Bonobo")
                .forEach(System.out::print); // MonkeyGorillaBonobo

        Stream s1 = Stream.of(1);
//        for (Integer i : s1){ } // DOES NOT COMPILE

        //reduce()
        //Java 7
        String[] array = new String[]{"w", "o", "l", "f"};
        String result = "";
        for (var s : array) {
            result = result + s;
        }
        System.out.println(result);

        //Java 8
        //1
        String string = Stream
                .of("w", "o", "l", "f")
                .reduce("", (s, c) -> s + c);
        System.out.println(string); // wolf

        string = Stream
                .of("w", "o", "l", "f")
                .reduce("", String::concat);
        System.out.println(string); // wolf

        //2
        Integer integer = Stream.of(3, 5, 6)
                .reduce(1, (a, b) -> a * b);
        System.out.println(integer);//90

        Optional<Integer> reduce = Stream.of(3, 5, 6)
                .reduce((a, b) -> a * b);
        System.out.println(reduce);//Optional[90]

        Stream.<Integer>empty()
                .reduce((a, b) -> a * b)
                .ifPresent(System.out::println); // no output

        Stream.of(3)
                .reduce((a, b) -> a * b)
                .ifPresent(System.out::println); // 3

        reduce
                .ifPresent(System.out::println); // 90

        //3
        Integer reduce1 = Stream.of("w", "o", "l", "f!")
                .reduce(0, (i, s) -> i + s.length(), (a, b) -> a + b);
        System.out.println(reduce1); // 5

        //collect()
        StringBuilder word = Stream
                .of("w", "o", "l", "f")
                .collect(StringBuilder::new,
                        StringBuilder::append, StringBuilder::append);

        TreeSet<String> set = Stream.of("w", "o", "l", "f") //sorted
                .collect(TreeSet::new,
                        TreeSet::add,
                        TreeSet::addAll);
        System.out.println(set); // [f, l, o, w]

        set = Stream.of("w", "o", "l", "f")
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set); // [f, l, o, w]

        Set<String> set1 = Stream.of("w", "o", "l", "f") //unsorted
                .collect(Collectors.toSet());
        System.out.println(set1); // [f, w, l, o]
    }
}
