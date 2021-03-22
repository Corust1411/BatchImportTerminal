package com.Corust1411.batch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.Scanner;

@ComponentScan(basePackages = {"com.Corust1411.batch"})
@SpringBootApplication
@EnableJpaRepositories("com.Corust1411.batch.repository")
@EnableAutoConfiguration(exclude = { UserDetailsServiceAutoConfiguration.class })
public class Application {

    public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
    }




    //////////////////
    public static void whileloop() {
        try {
            Scanner scanner = new Scanner(System.in);
            int a = 0;
            int number = 0;

            System.out.println("Testing for loop");
            System.out.print("Input some number : ");
            a = scanner.nextInt();

            while (a > 0) {
                a--;
                number++;
                System.out.println("Loop number " + number + " The input number " + a);
            }
            System.out.print("Finished the loop, the number is 0 now");
        } catch (Exception e) {
            System.out.println("whileloop > error > " + e.getMessage());
        }
    }
    public static void sortnumber() {
        try {
            int rows = 5;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(j);
                }
                System.out.print("\n");
            }

        } catch (Exception e) {
            System.out.println("\nsortnumber > error > " + e.getMessage());
        }
    }
    public static void sortnumber2() {
        try {
            int rows = 5;
            int max=5;
            for(int i = 1;i<= rows;i++){
                for(int j = 1;j<=5;j++){
                    if(j>(max-i)){
                        System.out.print(j);
                    }else{
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("\nsortnumber2 > error > " + e.getMessage());
        }
    }
    public static void sortnumber3() {
        try {
            int[]array={5,4,3,2,1};
            int rows = 5;
            int max=5;
            for(int i = 1;i<= rows;i++){
                for(int j = 1;j<=5;j++){
                    if(j>(max-i)){
                        System.out.print(array[j-1]);
                    }else{
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("\nsortnumber3 > error > " + e.getMessage());
        }
    }
    public static void sortnumber4() {
        try {
            int rows = 5;
            int columns = 9;
            for(int i = 1;i<=rows;i++){
                for(int l=1;l<=i;l++){
                    System.out.print(" ");
                }
                for(int j = 1;j<=9;j++){
                    if(i<=1){
                        System.out.print(j);
                    }else{
                        if(j<=columns) {
                            System.out.print(j);
                        }
                    }
                }
                columns-=2;
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("\nsortnumber4 > error > " + e.getMessage());
        }
    }
    public static void sortnumber5() {
        try {
            double rows = 9;
            int columns = 1;
            for(int i = 1;i<=rows;i++){
                if (i<=5) {
                    for (double l = Math.ceil(rows/2)-i+1; l > 1; l--) {
                        System.out.print(" ");
                    }
                }else{
                    for(int l=1;l<=i-Math.ceil(rows/2);l++){
                        System.out.print(" ");
                    }
                }
                for(int j = 1;j<=9;j++){
                    if(i<=5){
                        if(j<=columns) {
                            System.out.print(j);
                        }
                    }else{
                        if(j<=columns-4) {
                            System.out.print(j);
                        }
                    }
                }
                if(i<=5){
                    columns+=2;
                }else{
                    columns-=2;
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("\nsortnumber5 > error > " + e.getMessage());
        }
    }
}
