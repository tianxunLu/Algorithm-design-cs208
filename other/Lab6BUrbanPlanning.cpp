#include<cstdio>

constexpr int N=3e5+1e2;
// 存储图的邻接表表示，h为头结点，to为目标节点，nexp为下一个节点指针，d为节点的度数，p为边的计数器
int head[N],target[N],nexp[N],degree[N],p=1;
int n,u,v,x,k,ans;

inline int read(){
	char x=std::getchar();
	int lin=0, f=1;
	while(x<'0' or x> '9'){
		if(x=='-')
			f=-1;
		x=std::getchar();
	}
	while(x>='0' and x<='9'){
		lin=(lin<<1)+(lin<<3)+x-'0';
		x=std::getchar();
	}
	return lin*f;
}

inline void write(int x){
    if(x<0){
    	std::putchar('-');
		x=-x;
	}
    if(x>9)
		write(x/10);
    std::putchar(x%10+'0');
}

inline int dfs(int x,int father){
    if(degree[x] == 1){//is leaf
        return 1;
    }
    int dep=0;
    for(int u=head[x]; u; u=nexp[u]){//all adjacent
        if(target[u] != father){//not father
            int d=dfs(target[u], x);// 递归调用dfs函数计算邻接节点的深度
            if(d+dep>k){
                // 如果邻接节点的深度加上当前节点的深度大于k，说明当前节点与邻接节点之间的距离超过了k
                dep=dep<d?dep:d;// 更新dep为当前节点的深度和邻接节点的深度的较小值
                ++ans;// 计数器ans加1，表示找到了一对距离超过k的节点
                continue;
            }
            dep=d>dep?d:dep; // 更新dep为当前节点的深度和邻接节点的深度的较大值
        }
    }

    return dep?dep+1:0;// 返回当前节点的深度
}

int main(){
    n=read(),k=read();
    for(int i=0;i<n-1;++i){
    	u=read(),v=read();
		nexp[p]=head[u];
        head[u]=p, target[p]=v;// 添加边信息到邻接表中
		nexp[++p]=head[v];
        head[v]=p, target[p]=u;// 添加边信息到邻接表中
		++degree[u],++degree[v];// 更新节点的度数
		++p;// 边计数器加1
	}
    for(int i=1;i<=n;++i)
		if(degree[i] > 1){// 找到度数大于1的节点，即非叶节点
			write(ans+bool(dfs(i,0)));// bool(dfs(i,0))判断是否需要额外加1
            // 因为dfs(i,0)返回的是深度而不是节点数量
			return 0;
		}
	write(ans+1);// 输出答案，加上叶节点的个数
}
