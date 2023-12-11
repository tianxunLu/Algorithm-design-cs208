#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>

using namespace std;
int gcd(int a, int b)
{
    return b == 0 ? a : gcd(b, a % b);
}
const int maxn = 1e5+10;
const int log_max = log2(maxn) + 1;
//int stTABLE[maxn][maxn];
vector<vector<int>> stTABLE(log_max, vector<int>(maxn));
int find(int row,int step,int len){
    if (step>len){
        return 0;
    }else{
        for (int i = 0; i+step <= len; ++i) {//
            stTABLE[row][i] = gcd(stTABLE[row-1][i],stTABLE[row-1][i+step]);
        }
        step = step*2;
        return find(row+1,step,len-step+1);
    }
}

int gcdAll(int i,int right){
    int s = log2(right-i+1);
    int gcdl = stTABLE[s][i-1];
    unsigned int r = right-(1<<s)+1;
    int gcdr = stTABLE[s][r-1];
    return gcd(gcdl,gcdr);
}

int main(){
    int n=0;//n=num
    scanf("%d",&n);
    int initial[n];
    for (int i = 0; i < n; ++i) {
        int ai = 0;
        scanf("%d",&ai);
        initial[i] = ai;
    }
    int sub[n - 1];
    for (int i = 0; i < n - 1; ++i) {
        sub[i] = abs(initial[i] - initial[i + 1]);
        stTABLE[0][i] = sub[i];
    }
    find(1, 1, n - 2);
    //binary
    int maxnum = 0;
    for (int i = 1; i < n - 1; ++i) {
        int l = 1;
        int r = n - i; //
        while (l <= r) {
            int mid = (l + r) / 2;
            int here = gcdAll(i, i + mid - 1);
            if (here == 1) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l > maxnum) {
            maxnum = l;
        }
    }
    printf("%d", maxnum);
    return 0;
}
