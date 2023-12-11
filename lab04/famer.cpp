//
// Created by 86178 on 2023/3/17.
//
#include <iostream>
#include <queue>
#include <unordered_set>
#define linelength 200000

using namespace std;

int catchCow(int n, int k) {
    queue<pair<int, int>> q;
    unordered_set<int> visited;
    q.push(make_pair(n, 0));
    visited.insert(n);

    while (!q.empty()) {
        int curr = q.front().first;
        int steps = q.front().second;
        q.pop();

        if (curr == k) {
            return steps;
        }

        // left
        if (curr > 0 && visited.find(curr - 1) == visited.end()) {
            q.push(make_pair(curr - 1, steps + 1));
            visited.insert(curr - 1);
        }

        // right
        if (visited.find(curr + 1) == visited.end()) {
            q.push(make_pair(curr + 1, steps + 1));
            visited.insert(curr + 1);
        }

        // multiple
        if (curr * 2 <= linelength && visited.find(curr * 2) == visited.end()) {
            q.push(make_pair(curr * 2, steps + 1));
            visited.insert(curr * 2);
        }
    }

    return -1;
}

int famer() {
    int n, k;
    cin >> n >> k;
    cout << catchCow(n, k) << endl;
    return 0;
}

