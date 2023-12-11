#include<cstring>
#include<cstdio>
#include<vector>
using namespace std;
constexpr long long mod=1e9+7;
constexpr int N=2e2+1e1;
int t=1,child[N],sum,n,k,u,v;
long long FP[N][N],res,x,y;

struct Node{
	vector<Node*>kids;
	int w,a,b,idx;
}gp[N];

inline void exgcd(long long a,long long b){
	if(b==0)
		x=1,y=0;
	else {
		exgcd(b,a%b);
		long long tem=x;
		x=y;
		y=tem-a/b*y;
	}
}

void dfs(Node*node,int from){
        int idx=node->idx;
        child[idx]=1;
        for(int i=0;i<node->kids.size();++i) {
            Node*tmp=node->kids.at(i);
            int idxtmp=tmp->idx;
            if(idxtmp!=from){
                exgcd(tmp->b,mod);
                x=(x+mod)%mod;
                FP[idxtmp][1]=(tmp->a%mod)*(x%mod)%mod;
                FP[idxtmp][0]=((tmp->b-tmp->a+mod)%mod)*(x%mod)%mod;
                x=y=0;
                dfs(tmp,idx);
                child[idx]+=child[idxtmp];
                for(int j=child[idx];j>=1;--j){
                    FP[idx][j]=(FP[idx][j]%mod*FP[idxtmp][0]%mod)%mod;
                for(int k=1;k<=child[idxtmp]&&k<j;++k)
                    FP[idx][j]=
						(FP[idx][j]%mod+(FP[idx][j-k]%mod*FP[idxtmp][k]%mod)%mod)%mod;
            }
        }
    }
}

signed main(){
	scanf("%hd%d",&n,&k);
	for(int i=0;i<n-1;++i){
		scanf("%d%d",&u,&v);
		gp[u].idx=u,gp[v].idx=v;
		gp[u].kids.push_back(gp+v);
		gp[v].kids.push_back(gp+u);
	}
	for(int i=1;i<=n;++i){
		scanf("%d%d%d",&gp[i].w,&gp[i].a,&gp[i].b);
		sum+=gp[i].w;
	}
	while(t!=n+1){
		memset(child,0,sizeof(child));
		memset(FP,0,sizeof(FP));

		exgcd(sum,mod);

		x=(x+mod)%mod;
		FP[t][1]=((gp[t].w%mod)*(x%mod))%mod;
		x=y=0;

		dfs(gp+t,0);

		res=(res%mod+FP[t][k]%mod)%mod;
		++t;
	}
	printf("%lld\n",res);
}
