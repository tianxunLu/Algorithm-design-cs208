//
// Created by 86178 on 2023/4/9.
//
#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>

using namespace std;
int n; //vertices
int k; //no greater than
const int maxn = 1e5+10;
int edges[maxn]; //original
vector<int> father[maxn];
vector<int> child[maxn];
int mainb(){
    scanf("%d%d",&n,&k);
    for (int i = 0; i < n-1; ++i) {
        int a=0,b=0;
        scanf("%d,%d",&a,&b);
        a--;b--;
        father[a].push_back(b);
        child[b].push_back(a);
    }

    return 0;
}