package com.gmail.woodyc40.commons;

import com.gmail.woodyc40.commons.collect.AbstractHashStruct;
import com.gmail.woodyc40.commons.misc.Table;
import com.gmail.woodyc40.commons.providers.UnsafeProvider;
import lombok.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/*
A_TROLL implementation
Retrieving word list
Reading word list
Finished reading word list
Word count: 7999
Starting hash process
Finished hashing
Mapping...
Collisions: 49.86873359169896%
Average hash time: 9512ns
Done

JAVA implementation:
Retrieving word list
Reading word list
Finished reading word list
Word count: 7999
Starting hash process
Finished hashing
Mapping...
Collisions: 71.6589573696712%
Average hash time: 9813ns
Done

MURMUR implementation:
Retrieving word list
Reading word list
Finished reading word list
Word count: 7999
Starting hash process
Finished hashing
Mapping...
Collisions: 49.36867108388549%
Average hash time: 24499ns
Done
 */
public class HashFunctionMapper {
    @Getter private final Map<HashFunctionMapper.Coords, Long> map  = new HashMap<>();
    @Getter private final Collection<String>                   list = new ArrayList<>();

    @Getter private final List<Long> averageTimes = new ArrayList<>();

    public static void main(String... args) throws IOException {
        HashFunctionMapper mapper = new HashFunctionMapper();
        mapper.readWords();

        System.out.println("Starting hash process");
        for (String word : mapper.getList()) {
            long start = System.nanoTime();
            long hash = mapper.hash(word);
            long nanos = System.nanoTime() - start;

            mapper.getAverageTimes().add(nanos);

            if (!mapper.getMap().containsValue(hash)) {
                mapper.getMap().put(new HashFunctionMapper.Coords(hash, 1), hash);
            } else {
                int max = 1;
                for (HashFunctionMapper.Coords coords : mapper.getMap().keySet())
                    if (mapper.getMap().get(coords).equals(hash))
                        if (coords.getY() > max)
                            max = coords.getY();

                mapper.getMap().put(new HashFunctionMapper.Coords(hash, max + 1), hash);
            }
        }
        System.out.println("Finished hashing");

        System.out.println("Mapping...");
        mapper.printResults();
        System.out.println("Done");
    }

    public void readWords() throws IOException {
        System.out.println("Retrieving word list");
        URL url = new URL("http://www-01.sil.org/linguistics/wordlists/english/wordlist/wordsEn.txt");
        URLConnection connection = url.openConnection();
        @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        System.out.println("Reading word list");
        int i = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            i++;
            if (i == 8000)
                break;
            this.list.add(line);
        }
        System.out.println("Finished reading word list");

        System.out.println("Word count: " + this.list.size());
    }

    public void printResults() {
        int collisions = 0;
        for (Coords coords : this.map.keySet())
            if (coords.getY() > 1)
                collisions++;

        long total = 0L;
        for (Long t : this.averageTimes) {
            total += t;
        }

        System.out.println("Collisions: " + (double) collisions / (double) this.list.size() * 100.0 + '%');
        System.out.println("Average hash time: " + total / (long) this.averageTimes.size() + "ns");
    }

    public void map() {
        Table table = new Table();
        table.setNames("Hash", "Collisions");
        for (Long values : new HashSet<>(this.map.values())) {
            int max = 1;
            for (HashFunctionMapper.Coords coords : this.map.keySet())
                if (coords.getY() == values)
                    if (coords.getY() > max)
                        max = coords.getY();

            table.createRow().setColumn(0, String.valueOf(values)).setColumn(1, String.valueOf(max));
        }

        table.print(System.out);
    }

    long fnv_hash(Object o, Integer i) {
        long h = 2166136261L;

        for (int j = 0; j < i; j++) {
            h = (h * 16777619) ^ o.hashCode();
        }

        return UnsafeProvider.normalize(h) % i;
    }

    public long hash(String string) {
        //return (long) AbstractHashStruct.HashStrategy.A_TROLL.hash(string, map.size() + 1);
        //return (long) AbstractHashStruct.HashStrategy.JAVA.hash(string, this.map.size() + 1);
        return (long) AbstractHashStruct.HashStrategy.MURMUR.hash(string, this.map.size() + 1);
    }

    @Data
    public static class Coords {
        private final long x;
        private final int  y;
    }
}
