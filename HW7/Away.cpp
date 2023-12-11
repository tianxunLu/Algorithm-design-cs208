#include <iostream>
#include <cmath>
#include <cstdio>
#include <vector>

using namespace std;
const int maxn = 50;
int stTABLE[3][maxn];
long long n;
int level = 0;
int l, r;//n=num,l=left,r=right

long long find(long long n) {
    if (n <= 0) {
        return 0;
    }
    int pin = 0;
    long long sum = 0;
    for (int i = 0; i < level; ++i) {
        if (n == stTABLE[1][i]) {
            pin = i;
            sum = stTABLE[0][pin];
            break;
        } else if (n > stTABLE[1][i]) {
            pin = i;
            sum = stTABLE[0][pin] + stTABLE[2][pin];
            break;
        }
    }
    return sum + find(n - stTABLE[1][pin] - 1);
}

int main() {
    scanf("%lld%d%d", &n, &l, &r);
    level = log(n) / log(2);
    int len = pow(2, level + 1) - 1;
    for (int i = 0; i < level; ++i) {
        len = len / 2;
        stTABLE[1][i] = len;
    }
    for (int i = 0; i < level; ++i) {
        stTABLE[2][i] = n % 2;
        n = n / 2;
        stTABLE[0][i] = n;
    }
    int result = find(r) - find(l - 1);
    printf("%d", result);
    return 0;
}

