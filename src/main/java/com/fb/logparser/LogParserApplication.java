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
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

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
        Map<Integer, Integer> elapsedTime = new TreeMap<>();
        Map<Integer, Integer> bytes = new TreeMap<>();
        for (int i = 64396; i < 1707914; i++) {
            int ids = i - 64396;
            if (ids % 100000 == 0) {
                log.info(ids);
            }
            LogUnit logUnit = repository.findById(i);
//            log.info(logUnit);
//            String time = logUnit.getTime();
//            int index = time.indexOf('.');
//            if (index== -1 ){
//                index = time.length()-1;
//            }
//            time = time.substring(0, );
//            Date date = Date.from(Instant.ofEpochSecond(Long.valueOf(time)));
//            log.info(date);
            Integer elapsed = Integer.valueOf(logUnit.getElapsed());

            if (elapsedTime.containsKey(elapsed)) {
                int count = elapsedTime.get(elapsed) + 1;
                elapsedTime.put(elapsed, count);
            } else {
                elapsedTime.put(elapsed, 1);
            }
            Integer byteNumber = Integer.valueOf(logUnit.getBytes());
            if (bytes.containsKey(byteNumber)) {
                int count = bytes.get(byteNumber);
                bytes.put(byteNumber, count);
            } else {
                bytes.put(byteNumber, 1);
            }
        }
        BigInteger sumElapsed = BigInteger.valueOf(0);
        BigInteger count = BigInteger.valueOf(0);
        for (Map.Entry<Integer, Integer> entry : elapsedTime.entrySet()) {
            sumElapsed = sumElapsed.add(BigInteger.valueOf(entry.getKey()).multiply(BigInteger.valueOf(entry.getValue())));
            count = count.add(BigInteger.ONE);
        }
        BigInteger sumBytes = BigInteger.valueOf(0);
        count = BigInteger.valueOf(0);
        for (Map.Entry<Integer, Integer> entry : bytes.entrySet()) {
            sumBytes = sumBytes.add(BigInteger.valueOf(entry.getKey()).multiply(BigInteger.valueOf(entry.getValue())));
            count = count.add(BigInteger.ONE);
        }


        log.info(sumElapsed.divide(count));
        log.info(sumBytes.divide(count));
}

    public void saveToDatabase(String filePath, LogRepository repository) {
        Scanner sc = null;
        filePath = "C:\\Users\\Tempuser\\IdeaProjects\\logparser\\src\\main\\resources\\access.log";
        try {
            sc = new Scanner(new File(filePath));
            while (sc.hasNextLine()) {

                Scanner lineScanner = new Scanner(sc.nextLine());
                String line = lineScanner.toString();

                try {
                    LogUnit logUnit = new LogUnit(lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next(), lineScanner.next());

                    try {
                        repository.save(logUnit);
                    } catch (Exception e) {
                        log.error("Exception while saving" + logUnit.toString() + e.getMessage());
                    }
                    //log.info(logUnit.toString());
                } catch (NoSuchElementException e) {
                    log.error(line);
                }

            }
        } catch (FileNotFoundException e) {
            log.error("File not found" + e.getMessage());
        }
    }

}
