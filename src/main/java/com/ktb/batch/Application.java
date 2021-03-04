package com.ktb.batch;
import com.ktb.batch.config.AppConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class Application {
    private static AppConfig App = new AppConfig();
    public static void main(String[] args) {
        try {
            System.out.println("Batch Import Starting");
            ReadFile();
        } catch (Exception e) {
            System.out.println("\nmain > Error > " + e.getMessage());
        }
        finally {
            System.out.println("Batch Import Finished");
        }
    }
    public static String GetFileName(){

        String Pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Pattern);
        String OutputName;
        String date = simpleDateFormat.format(new Date());

        OutputName = App.GetSourceDirectory() + App.GetSourceFile().replace("{DATE}", date);

        return OutputName;
    }
    public static void ReadFile() {
        try {
            String path = GetFileName();
            String line = "";
            int count=0;
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if(count==0){
                    if (!ValidateHeader(values)) {
                        System.out.println("Header mismatch!");
                        break;
                    }
                }else{
                    String ValidateResult = ValidateDetail(values);
                    System.out.print(values[0]+"|"+values[1]+"|"+values[2]+"|"+values[3]+"|"+values[4]+"|"+values[5]+"|");
                    if(ValidateResult==null){
                        System.out.println("Result : successful");
                   }else{
                        System.out.println("Result : "+ValidateResult);
                    }
                }
                count++;
            }

        }catch(Exception e){
            System.out.print("\nReadFile > error > "+e.getMessage());
        }
    }
    public static boolean ValidateHeader(String[] values){
            if (!values[0].contains("merchantid")) return false;
            if (!values[1].contains("terminalid")) return false;
            if (!values[2].contains("location")) return false;
            if (!values[3].contains("effective")) return false;
            if (!values[4].contains("status")) return false;
            if (!values[5].contains("flag")) return false;
        return true;
    }
    public static String ValidateDetail(String[] values){
        String detail=null;
            if(values[0].length()>15){
                detail = "merchantID incorrect";
                return detail;
            }
            if(values[1].length()>8){
                detail = "TerminalID incorrect";
                return detail;
            }
            if(values[2].length()>100){
                detail = "Location incorrect";
                return detail;
            }
            if(values[3].length()>8){
                detail = "Effective incorrect";
                return detail;
            }
            if(values[4].length()>1){
                detail = "Status incorrect";
                return detail;
            }
            if(values[5].length()>1){
                detail = "Flag incorrect";
                return detail;
            }
        return detail;
    }
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
            System.out.println("\nwhileloop > error > " + e.getMessage());
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
