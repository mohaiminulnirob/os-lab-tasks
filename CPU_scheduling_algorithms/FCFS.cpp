#include <bits/stdc++.h>
using namespace std;

struct Process {
    string id;
    int arrival;
    int burst;
    int waiting;
    int turnaround;
    int response;
};

int main() {
    int n;
    cin >> n;

    vector<Process> processes(n);
    for (int i = 0; i < n; i++) {
        cin >> processes[i].id >> processes[i].burst >> processes[i].arrival;
    }

    sort(processes.begin(), processes.end(), [](Process& a, Process& b) {
        return a.arrival < b.arrival;
    });

    vector<pair<int,string>> timeline;
    int current_time = 0;
    double total_waiting = 0, total_turnaround = 0, total_response = 0;

    for (int i = 0; i < n; i++) {
        if (current_time < processes[i].arrival) {

            for (int t = current_time; t < processes[i].arrival; t++) {
                timeline.push_back({t,"IDLE"});
            }
            current_time = processes[i].arrival;
        }

        int start = current_time;
        for (int t = 0; t < processes[i].burst; t++) {
            timeline.push_back({current_time, processes[i].id});
            current_time++;
        }
        int end = current_time;

        processes[i].waiting = start - processes[i].arrival;
        processes[i].turnaround = end - processes[i].arrival;
        processes[i].response = processes[i].waiting; 

        total_waiting += processes[i].waiting;
        total_turnaround += processes[i].turnaround;
        total_response += processes[i].response;
    }

    cout << "\nTimeline:\n";
    for (const auto&u : timeline) {
        int second = u.first;
        string id = u.second;
        cout << "Time " << second << ": " << id << "\n";
    }

    cout << fixed << setprecision(2);
    cout << "\nAverage waiting time: " << total_waiting / n << "\n";
    cout << "Average turnaround time: " << total_turnaround / n << "\n";
    cout << "Average response time: " << total_response / n << "\n\n"; 

    cout << "Process\tArrival\tBurst\tWaiting\tTurnaround\tResponse\n";
    cout << "-----------------------------------------------------\n";
    for (const auto& p : processes) {
        cout << p.id << "\t" << p.arrival << "\t" << p.burst
             << "\t" << p.waiting << "\t" << p.turnaround << "\t\t" << p.response << "\n";
    }

    return 0;
}
