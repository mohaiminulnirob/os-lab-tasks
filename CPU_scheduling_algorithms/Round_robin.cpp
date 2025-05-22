#include <bits/stdc++.h>
using namespace std;

struct Process {
    string id;
    int arrival;
    int burst;
    int remaining;
    int start = -1;
    int completion = 0;

    int waiting = 0;
    int turnaround = 0;
    int response = 0;
};

int main() {
    int n, time_quantum;
    cin >> n >> time_quantum;

    vector<Process> processes(n);
    for (int i = 0; i < n; i++) {
        cin >> processes[i].id >> processes[i].burst >> processes[i].arrival;
        processes[i].remaining = processes[i].burst;
    }
    int max_arrival = 0;
    for (const auto& p : processes)
        max_arrival = max(max_arrival, p.arrival);
    vector<vector<int>> arrival_buckets(max_arrival + 100);

    for (int i = 0; i < n; i++) {
        arrival_buckets[processes[i].arrival].push_back(i);
    }

    queue<int> q;
    vector<pair<int,string>> timeline;
    int time = 0, completed = 0;
    vector<bool> in_queue(n, false); 

    while (completed < n) {
        for (int idx : arrival_buckets[time]) {
            if (!in_queue[idx]) {
                q.push(idx);
                in_queue[idx] = true;
            }
        }

        if (!q.empty()) {
            int idx = q.front();
            q.pop();

            if (processes[idx].start == -1)
                processes[idx].start = time;

            int exec_time = min(time_quantum, processes[idx].remaining);
            for (int i = 0; i < exec_time; i++) {
                timeline.push_back({time, processes[idx].id});
                time++;
                for (int j : arrival_buckets[time]) {
                    if (!in_queue[j]) {
                        q.push(j);
                        in_queue[j] = true;
                    }
                }
            }

            processes[idx].remaining -= exec_time;

            if (processes[idx].remaining == 0) {
                processes[idx].completion = time;
                processes[idx].turnaround = processes[idx].completion - processes[idx].arrival;
                processes[idx].waiting = processes[idx].turnaround - processes[idx].burst;
                processes[idx].response = processes[idx].start - processes[idx].arrival;
                completed++;
            } else {
                q.push(idx); 
            }
        } else {
            timeline.push_back({time, "IDLE"});
            time++;
        }
    }

    double total_waiting = 0, total_turnaround = 0, total_response = 0;
    for (const auto& p : processes) {
        total_waiting += p.waiting;
        total_turnaround += p.turnaround;
        total_response += p.response;
    }

    cout << "Timeline (second-wise):\n";
    for (const auto& u : timeline) {
        int sec = u.first;
        string id= u.second;
        cout << sec << ": " << id << "\n";
    }

    cout << fixed << setprecision(2);
    cout << "\nAverage waiting time: " << total_waiting / n << "\n";
    cout << "Average turnaround time: " << total_turnaround / n << "\n";
    cout << "Average response time: " << total_response / n << "\n";

    cout << "\nProcess\tArrival\tBurst\tWaiting\tTurnaround\tResponse\n";
    cout << "-------------------------------------------------------------\n";
    for (const auto& p : processes) {
        cout << p.id << "\t" << p.arrival << "\t" << p.burst << "\t"
             << p.waiting << "\t" << p.turnaround << "\t\t" << p.response << "\n";
    }

    return 0;
}
