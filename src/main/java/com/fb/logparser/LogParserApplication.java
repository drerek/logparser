package com.fb.logparser;

import com.fb.logparser.model.LogRepository;
import com.fb.logparser.model.LogUnit;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.time.Instant;
import java.util.*;

@SpringBootApplication
public class LogParserApplication implements CommandLineRunner {
    private static Logger log = Logger.getLogger(LogParserApplication.class);

    private final LogRepository repository;

    @Autowired
    public LogParserApplication(LogRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(LogParserApplication.class, args);
    }

    @Override
    public void run(String... args) {

    }

    //Save logUnit object to DB
    public void saveToDatabase(String filePath, LogRepository repository) {
        Scanner sc = null;
        try {
            //Scanner for reading file
            sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {
                //Scanner for reading line
                Scanner lineScanner = new Scanner(sc.nextLine());
                String line = lineScanner.toString();
                try {
                    log.debug("Trying to mapped line to object");
                    LogUnit logUnit = new LogUnit(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next());
                    try {
                        log.debug("Trying to save logUnit to database");
                        repository.save(logUnit);
                    } catch (Exception e) {
                        log.error("Exception while saving" + logUnit.toString() + e.getMessage());
                    }
                } catch (NoSuchElementException e) {
                    log.error("Line cant mapped to object" + line);
                }
            }
        } catch (FileNotFoundException e) {
            log.error("File not found" + e.getMessage());
        }
    }

    public BigInteger getAvgByteValue() {
        // Map of value:numbersOfInputs
        Map<Integer, Integer> bytes = new TreeMap<>();

        for (LogUnit logUnit : repository.findAll()) {
            Integer byteNumber = Integer.valueOf(logUnit.getBytes());
            if (bytes.containsKey(byteNumber)) {
                int count = bytes.get(byteNumber);
                bytes.put(byteNumber, count);
            } else {
                bytes.put(byteNumber, 1);
            }
        }
        return getAvgValue(bytes);
    }

    public BigInteger getAvgElapsedValue() {
        // Map of value:numbersOfInputs
        Map<Integer, Integer> elapsedTime = new TreeMap<>();

        for (LogUnit logUnit : repository.findAll()) {
            Integer elapsed = Integer.valueOf(logUnit.getElapsed());
            if (elapsedTime.containsKey(elapsed)) {
                int count = elapsedTime.get(elapsed) + 1;
                elapsedTime.put(elapsed, count);
            } else {
                elapsedTime.put(elapsed, 1);
            }
        }
        return getAvgValue(elapsedTime);
    }

    // Get avg value from map [value:counts]
    public BigInteger getAvgValue(Map<Integer, Integer> inputData) {
        BigInteger sum = BigInteger.valueOf(0);
        BigInteger count = BigInteger.valueOf(0);
        for (Map.Entry<Integer, Integer> entry : inputData.entrySet()) {
            sum = sum.add(BigInteger.valueOf(entry.getKey()).multiply(BigInteger.valueOf(entry.getValue())));
            count = count.add(BigInteger.ONE);
        }
        return sum.divide(count);
    }

    //Return map for [hour:entries]
    public Map getActivityByHours() {
        Map<Integer, Integer> activity = new TreeMap<>();
        for (LogUnit logUnit : repository.findAll()) {
            String time = logUnit.getTime();
            if (time.indexOf('.') != -1) {
                time = time.substring(0, time.indexOf('.'));

            }
            Date date = Date.from(Instant.ofEpochSecond(Long.valueOf(time)));
            int hour = date.getHours();
            if (activity.containsKey(hour)) {
                int count = activity.get(hour);
                activity.put(hour, count + 1);
            } else {
                activity.put(hour, 1);
            }
        }
        return activity;
    }
}
