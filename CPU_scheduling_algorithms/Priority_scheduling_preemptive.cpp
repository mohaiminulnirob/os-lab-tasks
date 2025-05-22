#include <bits/stdc++.h>
using namespace std;

struct Process {
    string id;
    int arrival;
    int burst;
    int priority;
    int remaining;
    int start = -1;
    int completion = 0;
    int waiting = 0;
    int turnaround = 0;
    int response = 0;
};

int main() {
    int n;
    cin >> n;

    vector<Process> processes(n);
    for (int i = 0; i < n; i++) {
        cin >> processes[i].id >> processes[i].burst >> processes[i].priority >> processes[i].arrival;
        processes[i].remaining = processes[i].burst;
    }

    int max_arrival = 0;
    for (const auto& p : processes) max_arrival = max(max_arrival, p.arrival);
    vector<vector<int>> arrival_buckets(max_arrival + 100);

    for (int i = 0; i < n; i++) {
        arrival_buckets[processes[i].arrival].push_back(i);
    }

    auto cmp = [&](int a, int b) {
        if (processes[a].priority == processes[b].priority)
            return processes[a].arrival > processes[b].arrival;
        return processes[a].priority > processes[b].priority;
    };

    priority_queue<int, vector<int>, decltype(cmp)> pq(cmp);
    vector<pair<int,string>> timeline;

    int time = 0, completed = 0;

    while (completed < n) {
        for (int idx : arrival_buckets[time]) {
            pq.push(idx);
        }

        if (!pq.empty()) {
            int idx = pq.top();
            pq.pop();

            if (processes[idx].start == -1)
                processes[idx].start = time;

            processes[idx].remaining--;
            timeline.push_back({time, processes[idx].id});

            if (processes[idx].remaining == 0) {
                processes[idx].completion = time + 1;

                processes[idx].turnaround = processes[idx].completion - processes[idx].arrival;
                processes[idx].waiting = processes[idx].turnaround - processes[idx].burst;
                processes[idx].response = processes[idx].start - processes[idx].arrival;

                completed++;
            } else {
                pq.push(idx);
            }
        } else {
            timeline.push_back({time, "IDLE"});
        }

        time++;
    }

    double total_waiting = 0, total_turnaround = 0, total_response = 0;
    for (const auto& p : processes) {
        total_waiting += p.waiting;
        total_turnaround += p.turnaround;
        total_response += p.response;
    }
    cout << "\nTimeline (second-wise):\n";
    for (const auto&u : timeline) {
        int sec=u.first;
        string id=u.second;
        cout << sec << ": " << id << "\n";
    }
    cout << fixed << setprecision(2);
    cout << "\nAverage waiting time: " << total_waiting / n << "\n";
    cout << "Average turnaround time: " << total_turnaround / n << "\n";
    cout << "Average response time: " << total_response / n << "\n";

    cout << "\nProcess\tArrival\tBurst\tPriority\tWaiting\tTurnaround\tResponse\n";
    cout << "--------------------------------------------------------------------------\n";
    for (const auto& p : processes) {
        cout << p.id << "\t" << p.arrival << "\t" << p.burst << "\t" << p.priority << "\t\t"
             << p.waiting << "\t" << p.turnaround << "\t\t" << p.response << "\n";
    }

    return 0;
}
