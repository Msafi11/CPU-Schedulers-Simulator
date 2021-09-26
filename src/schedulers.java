/*
 * Done all
 * */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import java.text.DecimalFormat;







public class schedulers {

    static process p;
     static WaterfallChartDemo w;
     static WaterfallChartDemo1 ww;

    static void sjf() {
        double avgwtime;
        double totalwtime = 0;
        double avgtatime;
        double totaltatime = 0;
        ArrayList<process> output = new ArrayList<>();
        List<process> list = p.getinput();


        Collections.sort(list, Comparator.comparing(process::getarrtime));


        process first = list.get(0);
        first.ctime = first.btime;
        double timenow = first.ctime;
        first.tatime = timenow - first.arrtime;
        totaltatime += first.tatime;
        first.wtime = first.tatime - first.btime;
        totalwtime += first.wtime;
        output.add(first);
        p.gui.add(first);
        list.remove(0);


        Collections.sort(list, Comparator.comparing(process::getbtime).thenComparing(process::getarrtime));


        for (int i = 0; i < list.size(); i++) {

            list.get(i).ctime = list.get(i).btime;
            timenow += list.get(i).ctime;
            list.get(i).tatime = timenow - list.get(i).arrtime;
            totaltatime += list.get(i).tatime;
            list.get(i).wtime = list.get(i).tatime - list.get(i).btime;
            totalwtime += list.get(i).wtime;
            output.add(list.get(i));
            p.gui.add(list.get(i));
            

        }

        System.out.println(output);


        avgtatime = totaltatime / (list.size() + 1);


        avgwtime = totalwtime / (list.size() + 1);

        System.out.println("-----------------");
        System.out.println("Turnaround Time  for process 1 " + '=' + first.tatime);
        for (int c = 0; c < list.size(); c++) {


            System.out.println("Turnaround Time  for process " + (c + 2) + '=' + list.get(c).tatime);

        }
        System.out.println("-----------------");
        System.out.println(" AVG Turnaround Time  for all processes = " + avgtatime);
        System.out.println("-----------------");
        System.out.println("waiting Time  for process 1 " + '=' + first.wtime);
        for (int c = 0; c < list.size(); c++) {


            System.out.println("waiting Time  for process " + (c + 2) + '=' + list.get(c).wtime);

        }
        System.out.println("-----------------");
        System.out.println(" AVG waiting Time  for all processes = " + avgwtime);
        w.main();

    }


    static void nonpreemtivepriority() {
        List<process> list = p.getinput();
        double avgwtime;
        double totalwtime = 0;
        double avgtatime;
        double totaltatime = 0;

        double min = 0;
        int index = 0;
        double max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i).PriorityNumber) {
                max = list.get(i).PriorityNumber;
                index = i;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (min > list.get(i).PriorityNumber) {
                min = list.get(i).PriorityNumber;
            }
        }


        if ((max - min) > 20) {
            for (int i = 0; i < list.size(); i++) {

                list.get(index).PriorityNumber = list.get(index).PriorityNumber - 1;
                Collections.sort(list, Comparator.comparing(process::getPriorityNumber));
            }


        } else {
            Collections.sort(list, Comparator.comparing(process::getPriorityNumber));
        }

        
        System.out.println(list.get(0).PriorityNumber);
        System.out.println(list);
        list.get(0).ctime = list.get(0).btime;
        for (int i = 1; i < list.size(); i++) {
            
            list.get(i).ctime = list.get(i).btime + list.get(i - 1).ctime;
        }
        
        p.gui.add(list.get(0));
        for(int i=1;i<list.size();i++) {
        	p.gui.add(list.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).tatime = list.get(i).ctime - list.get(i).arrtime;
            totaltatime += list.get(i).tatime;

        }
        avgtatime = totaltatime / list.size();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).wtime = list.get(i).tatime - list.get(i).btime;
            totalwtime += list.get(i).wtime;
        }
        avgwtime = totalwtime / list.size();

        System.out.println("-----------------");
        for (int c = 0; c < list.size(); c++) {


            System.out.println("Turnaround Time  for process " + (c + 1) + '=' + list.get(c).tatime);

        }
        System.out.println("-----------------");
        System.out.println(" AVG Turnaround Time  for all processes = " + avgtatime);
        System.out.println("-----------------");
        for (int c = 0; c < list.size(); c++) {


            System.out.println("waiting Time  for process " + (c + 1) + '=' + list.get(c).wtime);

        }
        System.out.println("-----------------");
        System.out.println(" AVG waiting Time  for all processes = " + avgwtime);
        
        w.main();

    }


    static void AG() {


        double avgwtime;
        double meanquantams = 0;
        double totalquantam = 0;
        double totalwtime = 0;
        double avgtatime;
        double totaltatime = 0;
        List Quantams[] = null;
        ArrayList<process> ready = new ArrayList<>();
       
        ArrayList<process> list = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        System.out.println("enter # of p: ");
        int listsize = input.nextInt();

        for (int i = 0; i < listsize; i++) {
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
            double AG_Factor = pironumber + arrtime + btime;
            list.add(new process(name, arrtime, btime, bttime, pironumber, Q, AG_Factor));

        }


        Collections.sort(list, Comparator.comparing(process::getarrtime));


        for (int i = 0; i < list.size(); i++) {
            totalquantam += list.get(i).Quantam;
        }
        meanquantams = Math.ceil(totalquantam / list.size());

        double totalbtime = 0;
        for (int i = 0; i < list.size(); i++) {
            totalbtime += list.get(i).btime;
        }


        int arrive = 1;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).ctime = 0;
        }


        process running = list.get(0);
        output.add(running);
        
        running.ctime += Math.ceil(running.Quantam / 2);
p.guii.add(running);
        running.btime -= Math.ceil(running.Quantam / 2);
        totalbtime -= Math.ceil(running.Quantam / 2);
         timenow = Math.ceil(running.Quantam / 2);
         outputtime.add((int) timenow);


        while (totalbtime != 0) {


            int minindex = 0;
            double minag = 0.0;


            if (arrive < list.size()) {

                if (list.get(arrive).arrtime <= timenow && list.get(arrive).AG_Factor < running.AG_Factor) {

                    running.Quantam += (running.Quantam - running.ctime);
                    System.out.println("new Quantam of " + running.name + ":" + running.Quantam);

                    running.ctime = 0;

                    ready.add(running);
                    running = list.get(arrive);

                    output.add(running);
                    running.ctime += Math.ceil(running.Quantam / 2);
                    p.guii.add(running);
                    running.btime -= Math.ceil(running.Quantam / 2);

                    totalbtime -= Math.ceil(running.Quantam / 2);
                    timenow += Math.ceil(list.get(arrive).Quantam / 2);
                    outputtime.add((int) timenow);

                    if (running.btime == 0) {
                        running.tatime = timenow - running.arrtime;
                        System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                        totaltatime += running.tatime;
                        running.wtime = running.tatime - running.bttime;
                        System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                        totalwtime += running.wtime;

                        running = ready.get(0);
                        output.add(running);
                        ready.remove(0);
                        running.ctime += Math.ceil(running.Quantam / 2);
                        p.guii.add(running);
                        running.btime -= Math.ceil(running.Quantam / 2);
                        totalbtime -= Math.ceil(running.Quantam / 2);
                        timenow += Math.ceil(running.Quantam / 2);
                        outputtime.add((int) timenow);


                    }
                    arrive++;


                } else if (list.get(arrive).arrtime <= timenow && list.get(arrive).AG_Factor > running.AG_Factor) {
                    ready.add(list.get(arrive));
                    arrive++;
                } else if (list.get(arrive).arrtime > timenow && ready.isEmpty()) {
                    double t1 = list.get(arrive).arrtime - timenow;
                    double t3 = t1;


                    if (t3 > 0 && t3 > Math.ceil(running.Quantam / 2)) {
                        t3 = Math.ceil(running.Quantam / 2);
                    }

                    if (t3 > running.btime && running.btime < running.Quantam) {
                        t3 = running.btime;
                    }


                    running.ctime += t3;
                    p.guii.add(running);
                    running.btime -= t3;
                    totalbtime -= t3;
                    timenow += t3;

                    if (running.btime == 0) {
                        running.tatime = timenow - running.arrtime;
                        System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                        totaltatime += running.tatime;
                        running.wtime = running.tatime - running.bttime;
                        System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                        totalwtime += running.wtime;

                        running = ready.get(0);
                        output.add(running);
                        ready.remove(0);
                        running.ctime += Math.ceil(running.Quantam / 2);
                        p.guii.add(running);
                        running.btime -= Math.ceil(running.Quantam / 2);
                        totalbtime -= Math.ceil(running.Quantam / 2);
                        timenow += Math.ceil(running.Quantam / 2);
                        outputtime.add((int) timenow);
                        output.add(running);
                    }


                } else if (list.get(arrive).arrtime > timenow && ready.isEmpty() == false) {

                    if (ready.size() == 1) {
                        minag = ready.get(0).AG_Factor;
                    } else {
                        minag = ready.get(0).AG_Factor;
                        for (int i = 1; i < ready.size(); i++) {
                            if (minag > ready.get(i).AG_Factor) {
                                minag = ready.get(i).AG_Factor;
                                minindex = i;

                            }
                        }
                    }

                    if (running.AG_Factor > minag) {
                        running.Quantam += (running.Quantam - running.ctime);
                        System.out.println("new Quantam of " + running.name + ":" + running.Quantam);

                        running.ctime = 0;
                        ready.add(running);
                        running = ready.get(minindex);
                        output.add(running);

                        ready.remove(minindex);


                        running.ctime += Math.ceil(running.Quantam / 2);
                        p.guii.add(running);
                        running.btime -= Math.ceil(running.Quantam / 2);
                        totalbtime -= Math.ceil(running.Quantam / 2);
                        timenow += Math.ceil(running.Quantam / 2);
                        outputtime.add((int) timenow);

                        if (running.btime == 0) {
                            running.tatime = timenow - running.arrtime;
                            System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                            totaltatime += running.tatime;
                            running.wtime = running.tatime - running.bttime;
                            System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                            totalwtime += running.wtime;

                            running = ready.get(0);
                            output.add(running);
                            ready.remove(0);
                            running.ctime += Math.ceil(running.Quantam / 2);
                            p.guii.add(running);
                            running.btime -= Math.ceil(running.Quantam / 2);
                            totalbtime -= Math.ceil(running.Quantam / 2);
                            timenow += Math.ceil(running.Quantam / 2);
                            outputtime.add((int) timenow);


                        }


                    } else if (running.AG_Factor < minag) {

                        double t1 = list.get(arrive).arrtime - timenow;
                        double t3 = t1;


                        if (t3 > 0 && t3 > Math.ceil(running.Quantam / 2)) {
                            t3 = Math.ceil(running.Quantam / 2);
                        }
                        if (t3 >= running.Quantam - (running.ctime)) {
                            t3 = running.Quantam - (running.ctime);
                        }
                        if (t3 >= running.btime && running.btime < running.Quantam) {
                            t3 = running.btime;
                        }

                        running.ctime += t3;
                        p.guii.add(running);
                        running.btime -= t3;
                        totalbtime -= t3;
                        timenow += t3;


                        if (running.btime == 0) {
                            running.tatime = timenow - running.arrtime;
                            System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                            totaltatime += running.tatime;
                            running.wtime = running.tatime - running.bttime;
                            System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                            totalwtime += running.wtime;


                            running = ready.get(0);
                            output.add(running);
                            ready.remove(0);
                            running.ctime += Math.ceil(running.Quantam / 2);
                            p.guii.add(running);
                            running.btime -= Math.ceil(running.Quantam / 2);
                            totalbtime -= Math.ceil(running.Quantam / 2);
                            timenow += Math.ceil(running.Quantam / 2);
                            outputtime.add((int) timenow);


                        } else if (running.ctime == running.Quantam && running.btime != 0) {
                            running.Quantam += Math.ceil((10 / 100) * meanquantams);
                            System.out.println("new Quantam of " + running.name + ":" + running.Quantam);

                            running.ctime = 0;


                            ready.add(running);
                            running = ready.get(0);
                            output.add(running);
                            ready.remove(0);
                            running.ctime += Math.ceil(running.Quantam / 2);
                            p.guii.add(running);
                            running.btime -= Math.ceil(running.Quantam / 2);
                            totalbtime -= Math.ceil(running.Quantam / 2);
                            timenow += Math.ceil(running.Quantam / 2);
                            outputtime.add((int) timenow);

                        }


                    }


                }
            } else if (arrive >= list.size()) {
                if (ready.isEmpty() == false) {
                    if (ready.size() == 1) {
                        minag = ready.get(0).AG_Factor;
                    } else {
                        minag = ready.get(0).AG_Factor;
                        for (int i = 1; i < ready.size(); i++) {
                            if (minag > ready.get(i).AG_Factor) {
                                minag = ready.get(i).AG_Factor;
                                minindex = i;

                            }
                        }
                    }
                    if (running.AG_Factor > minag) {
                        running.Quantam += (running.Quantam - running.ctime);
                        System.out.println("new Quantam of " + running.name + ":" + running.Quantam);

                        running.ctime = 0;
                        ready.add(running);
                        running = ready.get(minindex);
                        output.add(running);

                        ready.remove(minindex);

                        running.ctime += Math.ceil(running.Quantam / 2);
                        p.guii.add(running);
                        running.btime -= Math.ceil(running.Quantam / 2);
                        totalbtime -= Math.ceil(running.Quantam / 2);
                        timenow += Math.ceil(running.Quantam / 2);
                        outputtime.add((int) timenow);

                        if (running.btime == 0) {
                            running.tatime = timenow - running.arrtime;
                            System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                            totaltatime += running.tatime;
                            running.wtime = running.tatime - running.bttime;
                            System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                            totalwtime += running.wtime;


                            running = ready.get(0);
                            output.add(running);
                            ready.remove(0);
                            running.ctime += Math.ceil(running.Quantam / 2);
                            p.guii.add(running);
                            running.btime -= Math.ceil(running.Quantam / 2);
                            totalbtime -= Math.ceil(running.Quantam / 2);
                            timenow += Math.ceil(running.Quantam / 2);
                            outputtime.add((int) timenow);


                        }


                    } else if (running.AG_Factor < minag) {

                        double t3 = Math.ceil(running.Quantam / 2);
                        double t2 = running.Quantam - running.ctime;

                        if (t3 >= t2) {
                            t3 = t2;
                        }
                        if (t3 > running.btime && running.btime < running.Quantam) {
                            t3 = running.btime;
                        }

                        running.ctime += t3;
                        p.guii.add(running);
                        running.btime -= t3;
                        totalbtime -= t3;
                        timenow += t3;


                        if (running.btime == 0) {
                            running.tatime = timenow - running.arrtime;
                            System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                            totaltatime += running.tatime;
                            running.wtime = running.tatime - running.bttime;
                            System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                            totalwtime += running.wtime;


                            running = ready.get(0);
                            output.add(running);
                            ready.remove(0);
                            running.ctime += Math.ceil(running.Quantam / 2);
                            p.guii.add(running);
                            running.btime -= Math.ceil(running.Quantam / 2);
                            totalbtime -= Math.ceil(running.Quantam / 2);
                            timenow += Math.ceil(running.Quantam / 2);
                            outputtime.add((int) timenow);


                        } else if (running.ctime == running.Quantam && running.btime != 0) {
                            running.Quantam += Math.ceil((10 / 100) * meanquantams);
                            System.out.println("new Quantam of " + running.name + ":" + running.Quantam);

                            running.ctime = 0;

                            ready.add(running);
                            running = ready.get(0);
                            output.add(running);
                            ready.remove(0);
                            running.ctime += Math.ceil(running.Quantam / 2);
                            p.guii.add(running);
                            running.btime -= Math.ceil(running.Quantam / 2);
                            totalbtime -= Math.ceil(running.Quantam / 2);
                            timenow += Math.ceil(running.Quantam / 2);
                            outputtime.add((int) timenow);

                        }


                    }

                } else if (ready.isEmpty()) {
                    while (running.btime != 0) {
                        double t3 = Math.ceil(running.Quantam / 2);

                        double t2 = running.Quantam - running.ctime;

                        if (t3 >= t2) {
                            t3 = t2;
                        }

                        if (t3 > running.btime && running.btime < running.Quantam) {
                            t3 = running.btime;
                        }

                        running.ctime += t3;
                        p.guii.add(running);
                        running.btime -= t3;
                        totalbtime -= t3;
                        timenow += t3;


                    }
                    running.tatime = timenow - running.arrtime;
                    System.out.println("Turnaround Time  for process " + running.name + '=' + running.tatime);
                    totaltatime += running.tatime;
                    running.wtime = running.tatime - running.bttime;
                    System.out.println("waiting Time  for process " + running.name + '=' + running.wtime);
                    totalwtime += running.wtime;
                    running = null;


                    break;
                }


            }


        }
        System.out.println("-----------------");
        System.out.println(output);
        System.out.println("-----------------");


        avgtatime = totaltatime / list.size();


        avgwtime = totalwtime / list.size();


        System.out.println("-----------------");

        System.out.println("-----------------");
        System.out.println(" AVG Turnaround Time  for all processes = " + avgtatime);
        System.out.println("-----------------");

        System.out.println("-----------------");
        System.out.println(" AVG waiting Time  for all processes = " + avgwtime);
        
        
        
        ww.main();


    }
    public static double timenow = 0;
    
    public static ArrayList<process> output = new ArrayList<>();
    public static ArrayList<Integer> outputtime = new ArrayList<>();
   

    public int[] calculateWaitingTime(process p[], int n,
                                      int waitingTime[], int contextSwitch) {
        System.out.println("=======================");
        System.out.println("==========================================");
        ArrayList<Integer> excecutionOrder = new ArrayList<>();
        int remainingTime[] = new int[n];
        int completionTime[] = new int[n];

        for (int i = 0; i < n; i++)
            remainingTime[i] = (int) p[i].btime;

        int numOfCompletedProcesses = 0, time = 0, minimum = Integer.MAX_VALUE;
        int indexOfShortest = 0, finish_time;
        boolean check = false;
        process prev = null;

        while (numOfCompletedProcesses != n) {

            for (int j = 0; j < n; j++) {
                if ((p[j].arrtime <= time) &&
                        (remainingTime[j] < minimum) && remainingTime[j] > 0) {
                    minimum = remainingTime[j];
                    indexOfShortest = j;
                    check = true;
                }
            }

            if (check == false) {
                time++;
                continue;
            }
            if (prev != null && p[indexOfShortest] != prev) {
                time += contextSwitch;

                prev = null;
                continue;
            } else if (prev == null)
                excecutionOrder.add(indexOfShortest + 1);

            remainingTime[indexOfShortest]--;
            prev = p[indexOfShortest];

            minimum = remainingTime[indexOfShortest];
            if (minimum == 0)
                minimum = Integer.MAX_VALUE;

            if (remainingTime[indexOfShortest] == 0) {


                numOfCompletedProcesses++;
                check = false;

                finish_time = time + 1;
                completionTime[indexOfShortest] = finish_time;

                waitingTime[indexOfShortest] = (int) (finish_time -
                        p[indexOfShortest].btime -
                        p[indexOfShortest].arrtime);
                time += contextSwitch;
                prev = null;

            }

            time++;
        }
       
        System.out.println("Excecution Order : ");
        for (int i = 0; i < excecutionOrder.size(); i++)
        	
            System.out.println(excecutionOrder.get(i));

        return completionTime;
    }

    public ArrayList<Integer> executionOrder(int[] array, int n) {
        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            indices.add(i);
            order.add(array[i - 1]);
        }
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (order.get(i) > order.get(j)) {
                    temp = order.get(i);
                    order.set(i, order.get(j));
                    order.set(j, temp);

                    temp = indices.get(i);
                    indices.set(i, indices.get(j));
                    indices.set(j, temp);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            order.set(i, i + 1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (indices.get(i) > indices.get(j)) {
                    temp = order.get(i);
                    order.set(i, order.get(j));
                    order.set(j, temp);

                    temp = indices.get(i);
                    indices.set(i, indices.get(j));
                    indices.set(j, temp);
                }
            }
        }
        return order;
        
    }

    public void calculateTurnaroundTime(process p[], int n,
                                        int waitingTime[], int turnaroundTime[]) {

        for (int i = 0; i < n; i++)
            turnaroundTime[i] = (int) (p[i].btime + waitingTime[i]);
    }

    

    public void calculate(process p[], int n, int contextSwitch) {
    	
        int waitingTime[] = new int[n], turnaroundTime[] = new int[n], completionTime[];
        ArrayList<Integer> executionOrder;
        double totalWaitingTime = 0, totalTurnaroundTime = 0;

        completionTime = calculateWaitingTime(p, n, waitingTime, contextSwitch);
        executionOrder = executionOrder(completionTime, n);

        calculateTurnaroundTime(p, n, waitingTime, turnaroundTime);
        

        System.out.println("Processes \t" +
                "Arrival Time \t" +
                "Burst time \t" +
                " Waiting time \t" +
                "Turnaround time \t" +
                "Completion Time \t" +
                "End order");

        for (int i = 0; i < n; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
            System.out.println(p[i].name + "\t\t\t"
                    + p[i].arrtime + "\t\t\t\t"
                    + p[i].btime + "\t\t\t "
                    + waitingTime[i] + "\t\t\t\t"
                    + turnaroundTime[i] + "\t\t\t\t\t"
                    + completionTime[i] + "\t\t\t\t\t"
                    + executionOrder.get(i));
            
        }

        System.out.println("Average waiting time = " +
                totalWaitingTime / n);
        System.out.println("Average turn around time = " +
                totalTurnaroundTime / n);
    }

    public void SRTF() {
    	
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int numOfPrcoesses = scanner.nextInt();
        process p[] = new process[numOfPrcoesses];
        for (int i = 0; i < numOfPrcoesses; i++) {
            System.out.print("Enter the ID of process " + (i + 1) + ": ");
            String processID = scanner.next();
            System.out.print("Enter the arrival time of process " + (i + 1) + ":");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter the burst time of process " + (i + 1) + ":");
            int burstTime = scanner.nextInt();
            p[i] = new process(processID, arrivalTime, burstTime);
        }
        System.out.print("Enter context switch: ");
        int contextSwitch = scanner.nextInt();
        calculate(p, numOfPrcoesses, contextSwitch);
    }
    
    
    
    
    
}











