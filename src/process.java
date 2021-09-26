

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class process {
    public String name;
    public double arrtime;
    public double btime;
    public double bttime;
    public double PriorityNumber;
    public double ctime;
    public double tatime;

    public double wtime;
    public double Quantam;
    public double AG_Factor;


    public process(String name,double arrtime, double btime,double bttime,double PriorityNumber,double Quantam,double AG_Factor ) {

        this.name = name;
        this.arrtime = arrtime;
        this.btime = btime;
        this.bttime = bttime;
        this.PriorityNumber = PriorityNumber;
        this.Quantam=Quantam;
        this.AG_Factor=AG_Factor;
    }
    public process(String processID, int arrivalTime, int burstTime)
    {
        this.name = processID;
        this.arrtime = arrivalTime;
        this.btime = burstTime;
    }
    public String getname() {
        return name;
    }
    public double getQuantam() {
        return Quantam;
    }
    public double getarrtime() {
        return arrtime;
    }
    public double getbtime() {
        return bttime;
    }
    public double getbttime() {
        return btime;
    }
    public double getPriorityNumber() {
        return PriorityNumber;
    }
    public double getAG_Factor() {
        return PriorityNumber+arrtime+btime;
    }


    @Override
    public String toString() {
        return name;
    }

    public static double calcmean(ArrayList<process> ready) {

        double total=0;
        for(int i=0;i<ready.size();i++) {
            total +=ready.get(i).Quantam;
        }
        return (total/ready.size());
    }

    public static ArrayList<process> getinput() {

        ArrayList<process> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("enter # of p: ");
        int listsize = input.nextInt();

        for(int i=0;i<listsize;i++) {
            System.out.println("enter name of p: ");
            String name = input.next();
            System.out.println("enter pironumber of p: ");
            double pironumber = input.nextInt();
            System.out.println("enter arrtime of p: ");
            double arrtime = input.nextInt();
            System.out.println("enter btime of p: ");
            double btime = input.nextInt();
            System.out.println("enter const btime of p: ");
            double bttime = input.nextInt();
            System.out.println("enter Quantam of p: ");
            double Q = input.nextInt();
            double AG_Factor =pironumber+arrtime+btime;
            list.add(new process(name, arrtime,btime,bttime,pironumber,Q,AG_Factor));

        }
        return list;
    }
    
    public static ArrayList<process> gui = new ArrayList<>();
    public static ArrayList<process> guii = new ArrayList<>();


}
