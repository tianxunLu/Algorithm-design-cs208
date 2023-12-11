#include<bits/stdc++.h>
// C++标准库头文件，省去了一些常用的头文件的包含
#define M 20005
#define N 2005
#define ll long long
using namespace std;
// 定义一些常量和类型别名，便于后续代码的编写
const int inf=1e9;
// 无限大
void Rd(int &res) {
// 快读，加快输入速度
    res=0;
    char c;
    int fl=1;
    while(c=getchar(),c<48)if(c=='-')fl=-1;
    do res=(res<<1)+(res<<3)+(c^48);
    while(c=getchar(),c>=48);
    res*=fl;
}
struct node {
// 定义结构体，保存图中的边
    int x,y,v;
} A[M<<1];
// 因为每个点都需要与其他点相连，所以边的数量最多是 n(n-1)/2
// 每条边拆成两个方向之后，边数会变成 2n(n-1)，再加上后面计算
// 好的虚拟边，因此数组长度要乘以 2
int In[N],pre[N],vis[N],Id[N],id;
// In：存储到达每个点的最小边权值
// pre：存储到达每个点最小边所经过的边的起点
// vis：标记每个点是否被访问过
// Id：给点重新编号，去掉中间的断点
// id：存储到达根节点的最小边的编号
ll calc(int r,int n,int m) {
// 求以 r 为根的最小生成树，返回其权值
    ll sum=0;
    while(1) {
        for(int i=0; i<n; i++)In[i]=inf;
        // 初始化 In 数组
        for(int i=0; i<m; i++) {
            // 扫描每一条边
            int x=A[i].x,y=A[i].y;
            if(In[y]>A[i].v&&x!=y) {
                // 如果有更小的边，更新 In 和 pre 数组
                In[y]=A[i].v,pre[y]=x;
                if(x==r)id=i;
            }
        }
        for(int i=0; i<n; i++) {
            // 判断是否存在到达某个点的最小边
            if(i==r)continue;
            // 根节点到达它自身的最小边权值为 0
            if(In[i]==inf)return -1;
        }
        // 如果有点未到达，则说明不存在最小生成树
        int cnt=0;
        // 重新编号
        for(int i=0;i<n;i++)Id[i]=vis[i]=-1;
        In[r]=0;
        // 给点重新编号
        for(int i=0; i<n; i++) {
            sum+=In[i];
            int x=i;
            while(vis[x]!=i&&Id[x]==-1&&x!=r)vis[x]=i,x=pre[x];
            // 判断是否
			if(x!=r&&Id[x]==-1) {
				int y=pre[x];
				while(y!=x)Id[y]=cnt,y=pre[y];
				Id[x]=cnt++;
			}
		}
		if(cnt==0)break;
		for(int i=0; i<n; i++)if(Id[i]==-1)Id[i]=cnt++;
		for(int i=0; i<m; i++) {
			int x=A[i].x,y=A[i].y; 
			A[i].x=Id[x];
			A[i].y=Id[y];
			if(A[i].x!=A[i].y)A[i].v-=In[y];
		}
		n=cnt,r=Id[r];
	}
	return sum;
}

int mainB() {
    int n,m;
	scanf("%d%d",&n,&m);
	int sum=0;
	for(int i=0; i<m; i++) {
		scanf("%d%d%d",&A[i].x,&A[i].y,&A[i].v);
		sum+=A[i].v;
	}
	sum++;
	for(int i=m; i<m+n; i++)A[i]=(node)<%n,i-m,sum%>;
	ll now=calc(n,n+1,n+m);
	if(now==-1||now>=2*sum)puts("impossible");
	else printf("%lld %d\n",now-sum,id-m);
	return 0;
}
