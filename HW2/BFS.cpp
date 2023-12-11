//
// Created by 86178 on 2023/3/17.
//
#include<iostream>
using namespace std;

int BFS(){    //  数组队列实现  bfs遍历无向图
    int i,j,a,b,n,m,cur,book[101]={0},e[101][101];
    int que[10001],head,tail;
    cin>>n>>m;
    for(i=1;i<=n;i++)
        for(j=1;j<=n;j++)
            if(i==j) e[i][j]=0;
            else e[i][j]=99999999;
    for(i=1;i<=m;i++){
        cin>>a>>b;
        e[a][b]=1;
        e[b][a]=1;
    }
    head=1;tail=1;
    que[tail++]=1;
    book[1]=1;
    while(head<tail && tail<=n){
        cur=que[head];
        for(i=1;i<=n;i++){
            if(book[i]==0 && e[cur][i]==1){
                que[tail++]=i;
                book[i]=1;
            }
            if(tail>n) break;
        }
        head++;
    }
    for(i=1;i<tail;i++) cout<<que[i]<<" ";
    return 0;
}

