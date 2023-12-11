//
// Created by 86178 on 2023/3/17.
//
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Person {
public:
    int id;
    vector<int> preference; // 偏好列表
    int partner; // 已婚配偶的编号，未婚时为-1
    int next; // 下一个要考虑的人的偏好列表下标

    Person(int id, vector<int>& preference) : id(id), preference(preference), partner(-1), next(0) {}
};

// 稳定婚姻匹配算法的实现
vector<pair<int, int>> stableMatching(vector<vector<int>>& menPreferences, vector<vector<int>>& womenPreferences) {
    int n = menPreferences.size();

    vector<Person> men, women;
    for (int i = 0; i < n; i++) {
        men.push_back(Person(i, menPreferences[i]));
        women.push_back(Person(i, womenPreferences[i]));
    }

    vector<bool> womenEngaged(n, false); // 记录女人是否已经订婚
    vector<int> freeMen; // 自由男人的编号
    for (int i = 0; i < n; i++) {
        freeMen.push_back(i);
    }

    while (!freeMen.empty()) {
        int man = freeMen.back(); // 取出一个自由男人
        freeMen.pop_back();

        int womanId = men[man].preference[men[man].next++]; // 找到他喜欢的第一个女人
        Person& woman = women[womanId];

        if (woman.partner == -1) { // 女人还没有订婚
            woman.partner = man;
            men[man].partner = womanId;
            womenEngaged[womanId] = true;
        } else if (find(woman.preference.begin(), woman.preference.end(), man) < find(woman.preference.begin(), woman.preference.end(), woman.partner)) {
            // 女人更喜欢现在的这个男人
            int oldMan = woman.partner;
            freeMen.push_back(oldMan); // 老男人变成了自由男人
            men[oldMan].partner = -1;
            woman.partner = man; // 新男人成为了女人的订婚对象
            men[man].partner = womanId;
        } else {
            // 女人更喜欢她现在的订婚对象
            freeMen.push_back(man); // 这个男人仍然是自由的
        }
    }

    vector<pair<int, int>> result;
    for (int i = 0; i < n; i++) {
        result.push_back(make_pair(i, men[i].partner));
    }
    return result;
}































