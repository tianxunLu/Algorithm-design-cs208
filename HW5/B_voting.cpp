#include <iostream>
#include <algorithm>
#include <cstring>
#include <queue>

using namespace std;
struct node_B{
    int m;
    int c;
};
bool cmp(node_B a,node_B b){
    return b.m<a.m;
}
int mainb(){
    int T; //tests
    scanf("%d",&T);
    for (int i = 0; i < T; ++i) {
        int n; //#classmates
        scanf("%d",&n);
        int count[n];
        memset(count, 0, sizeof(count));
        node_B arr[n];
        vector<int> arrLists[n];
        priority_queue<int, vector<int>, greater<int>> hier;
        for (int j = 0; j < n; ++j) {
            int m = 0;
            int c = 0;
            scanf("%d%d",&m,&c);
            arr[j] =(node_B)<%m,c%>;
            count[m]++;
            arrLists[m].push_back(c);
        }
        for (int j = n-1; j >= 0; --j) {
            if (j!=n-1){
                count[j]+=count[j+1];
            }
        }
        //sort by m
        sort(arr,arr+n, cmp);
        long sum = 0;
        int present=0; //present students
        for (int j = 0; j < n; ++j) {
            node_B pre_n = arr[j];
            int len = arrLists[pre_n.m].size();
            for (int k = 0; k < len; ++k) {
                hier.push(arrLists[pre_n.m][0]);
                arrLists[pre_n.m].erase(arrLists[pre_n.m].begin());
            }
            int leave = n-count[pre_n.m];
            if (leave+present<pre_n.m){
                sum+=hier.top();
                hier.pop();
                present++;
            }
        }
        printf("%ld\n",sum);
    }
    return 0;
}