


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import java.text.DecimalFormat;

public class main {
	
    public static void main(String... args) {
        Scanner input = new Scanner(System.in);
        while (true) {


            System.out.println("Welcome to our cpu schedulers!!");
            System.out.println("1-Non-Preemptive  Shortest- Job  First (SJF)");
            System.out.println("2-Shortest- Remaining Time First (SRTF)  Scheduling");
            System.out.println("3-Non-preemptive  Priority Scheduling");
            System.out.println("4-AG  Scheduling");
            System.out.println("5-Quit!");
            System.out.println("Choose a scheduler: ");
            int scheduler = input.nextInt();
            schedulers s = new schedulers();
            if (scheduler == 1) {
                s.sjf();
            } else if (scheduler == 2) {
                s.SRTF();
            } else if (scheduler == 3) {
                s.nonpreemtivepriority();
            } else if (scheduler == 4) {
                s.AG();
            } else if (scheduler == 5) {
                System.exit(0);
            }

        }


    }
}

