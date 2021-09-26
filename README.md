# CPU-Schedulers-Simulator
Scheduling is a fundamental operating-system function. Almost all computer resources are 
scheduled before use. The CPU is, of course, one of the primary computer resources. Thus, 
its scheduling is central to operating-system design. CPU scheduling determines which 
processes run when there are multiple run-able processes. CPU scheduling is important 
because it can have a big effect on resource utilization and the overall performance of the 
system.

## Simulate the following schedulers:
1-Non-Preemptive Shortest- Job First (SJF).
2-Shortest- Remaining Time First (SRTF) Scheduling using context switching
3-Non-preemptive Priority Scheduling (with the solving of starvation problem using
any way can be executed correctly).
4-AG Scheduling:
a-The Round Robin (RR) CPU scheduling algorithm is a fair scheduling 
algorithm that gives equal time quantum to all processes So All processes
are provided a static time to execute called quantum.
b-A new factor is suggested to attach with each submitted process in our AG 
scheduling algorithm. This factor sums the effects of all three basic factors 
(priority, arrival time and burst time). The equation summarizes this relation 
is: *** AG-Factor = Priority + Arrival Time + Burst Time ***
c-Once a process is executed for given time period, it’s called 
Non-preemptive AG till the finishing of (ceil (50%)) of its Quantum time, 
after that it’s converted to preemptive AG.
d-We have 3 scenarios of the running process:
i-The running process used all its quantum time and it still have job to 
do (add this process to the end of the queue, then increases its 
Quantum time by (ceil(10% of the (mean of Quantum))) ).
ii-The running process didn’t use all its quantum time based on another 
process converted from ready to running (add this process to the end 
of the queue, and then increase its Quantum time by the remaining 
unused Quantum time of this process).
iii-The running process finished its job (set its quantum time to zero
and remove it from ready queue and add it to the die list).
