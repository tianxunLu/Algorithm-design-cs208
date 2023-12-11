#include <cmath>
#include <cstdio>

using namespace std;
long long n;
int l,r;//n=num,l=left,r=right

long long find(long long len,long long r,long long n){
    if (r==1){
        return 1;
    }
    if (r==0){
        return 0;
    }
    if (r>len/2+1){
        return n- find(len/2,len-r,n/2);
    }else if (r==len/2){
        return n/2;
    }else if (r==len/2+1){
        return n/2+n%2;
    }else{
        return find(len/2,r,n/2);
    }
}

int main(){
    scanf("%lld%lld%lld",&n,&l,&r);
//    r = r-1;l = l-1;
    long long level = log(n)/log(2);
    long long len = pow(2,level+1)-1;
    int a = find(len,r,n);
    int b = find(len,l-1,n);
    printf("%d",a-b);
    return 0;
}

