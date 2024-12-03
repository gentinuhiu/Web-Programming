package mk.ukim.finki.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.time.LocalDateTime;

@SpringBootApplication
@ServletComponentScan
public class Lab1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
        int port = 9090;
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime + " \nHyper Text Transfer Protocol: 200 STATUS OK\nServer is listening on PORT: " + port + " (HTTP)");
    }

}
