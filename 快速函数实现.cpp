#include<bits/stdc++.h>
#pragma GCC optimize(3)
#pragma GCC optimize("Ofast")
#pragma GCC optimize("inline")
#pragma GCC optimize("-fgcse")
#pragma GCC optimize("-fgcse-lm")
#pragma GCC optimize("-findirect-inlining")
#pragma GCC optimize("-fhoist-adjacent-loads")
#pragma GCC optimize("-frerun-cse-after-loop")
#pragma GCC optimize("inline-small-functions")
#pragma GCC optimize("-finline-small-functions")
#pragma GCC optimize("-ftree-switch-conversion")
#pragma GCC optimize("-foptimize-sibling-calls")
#pragma GCC optimize("-fexpensive-optimizations")
#pragma GCC optimize("-funsafe-loop-optimizations")
#pragma GCC optimize("inline-functions-called-once")
#pragma GCC optimize("-fdelete-null-pointer-checks")

using namespace std;
inline int gcd(int x,int y){
	return y?gcd(y,x%y):x;
}

inline string readStr(){
	string str="";
	char ch=getchar();
	while(ch==' '||ch=='\n'||ch=='\r')
		ch=getchar(); 
	while(ch!=' '&&ch!='\n'&&ch!='\r')
	{
		str+=ch;
		ch=getchar();
	 } 
	return str;
}

inline void printStr(string s){
	for(int i=0;s[i]!='\0';++i) 
		putchar(s[i]);
}
inline int read(){
	char x=getchar();
	int lin=0, f=1;
	while(x<'0'||x> '9'){
		if(x=='-')
			f=-1;
		x=getchar();
	}
	while(x>='0'&&x<='9'){
		lin=(lin<<1)+(lin<<3)+x-'0';
		x=getchar();
	}
	return lin*f;
}

inline void lread(long long &a){
	a=0; 
	char c;
	while((c=getchar())<48);
	do a=a*10+(c^48);
	while((c=getchar())>47);
}


inline void write(int x){
    if(x<0){
    	putchar('-');
		x=-x;
	}
    if(x>9) 
		write(x/10);
    putchar(x%10+'0');
}
inline float qsqrt(float x){
    float xhalf = 0.5f*x;
    int i = *(int*)&x;
    i = 0x5f3759df - (i>>1);
    x = *(float*)&i;
    x = x*(1.5f - xhalf*x*x);
    return x;
}

inline long long qlog(long long val){
	long long ans=0;
	while((val>>=1)!=0)
		++ans;
	return ans;
}

inline long long qpow(long long x,long long y){
	long long sum=1;
	while(b){
		if(y&1)
			sum*=x%m;
		x*=x%m;
		y>>=1;
	}
	return sum;
}

