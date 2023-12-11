#include <iostream>
using namespace std;
struct edge{
    int to;
    int next;
};
/*
4 3 2 3
2 1
2 3
4 2
0
1
2
*/
int lab1() {
    static int n,m,p,q; //n--vertice,m--edges,p--initial,q--questions
    std::cin >> n >> m >> p >> q;
    int head[n+1][m];
    int pointer[n+1];
    edge roads[m];
    for (int i = 0; i < m; ++i) {
        int v1,v2;
        std::cin >> v1 >> v2;
        roads[v1].to = v2;
        roads[v1].next = head[v1][pointer[v1]];
        head[v1][++pointer[v1]] = i*2-1;
        roads[v2].to = v2;
        roads[v2].next = head[v2][pointer[v2]];
        head[v2][pointer[v2]++] = i*2;
    }


    //output
    for (int i = 0; i < q; ++i) {
        std::cout <<" " << std::endl;
    }
    return 0;
}

