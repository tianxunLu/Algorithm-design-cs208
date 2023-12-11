#include <algorithm>
#include <cmath>
#include <iostream>
#include <vector>
#define int long long
using namespace std;
int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
const int maxn = 1e5 + 10;
const int log_max =21;
vector<vector<int>> stTABLE(log_max, vector<int>(maxn));
int find(int row, int step, int len) {
    if (step > len) {
        return 0;
    } else {
        for (int i = 0; i + step <= len; ++i) { //
            stTABLE[row][i] = gcd(stTABLE[row - 1][i], stTABLE[row - 1][i + step]);
        }
        step = step * 2;
        return find(row + 1, step, len - step+1);
    }
}

int gcdAll(int i, int right) {
    int s = log2(right - i + 1);
    int gcdl = stTABLE[s][i - 1];
    unsigned int r = right - (1<<s) + 1;
    int gcdr = stTABLE[s][r - 1];
    return gcd(gcdl, gcdr);
}

signed main() {
    int n = 0;
    scanf("%lld", &n);
    int initial[n];
    for (int i = 0; i < n; ++i) {
        int ai = 0;
        scanf("%lld", &ai);
        initial[i] = ai;
    }
    int sub[n - 1];
    for (int i = 0; i < n - 1; ++i) {
        sub[i] = abs(initial[i] - initial[i + 1]);
        stTABLE[0][i] = sub[i];
    }

    int ans = 0;
    find((int)1, (int)1, n - 2);
    int maxnum = 0;
    for (int i = 1; i < n - 1; ++i) {
        int l = i;
        int r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int here = gcdAll(i, mid);
            if (here == 1) {
                r = mid - 1;
            } else {
                maxnum = max (maxnum,mid-i+1);
                l = mid + 1;
            }
        }

    }
    printf("%lld", maxnum+1);
    return 0;
}
