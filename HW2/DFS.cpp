//
// Created by 86178 on 2023/3/17.
//
#include<iostream>
using namespace std;

int sum=0,n,m,book[101]={0},e[101][101];
void dfs(int cur){
    if(sum==n) return;
    cout<<cur<<" ";
    sum++;
    for(int i=1;i<=n;i++){
        if(book[i]==0 && e[cur][i]==1){
            book[i]=1;
            dfs(i);
            //这里是无向图，不需要回溯
        }
    }
    return;
}
int DFS(){    //  递归实现  dfs遍历无向图
    int i,j,a,b;
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
    book[1]=1;
    dfs(1);
    return 0;
}
