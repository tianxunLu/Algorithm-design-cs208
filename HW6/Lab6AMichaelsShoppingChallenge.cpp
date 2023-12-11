#include<bits/stdc++.h>

using namespace std;
constexpr int N = 5e4 + 1e2;
priority_queue<long> diff;// 优先队列，用于存储价格差值
int n, k, res, p, c;
long long m, u, v;// 输入变量和临时变量
bool vis[N];// 记录商品是否被选中

struct item {
    long long p, c;
    int idx;// 商品索引
} a[N], b[N], temp;// a和b分别为按照折扣价格和原始价格排序后的商品数组

inline bool cmpA(const item &x, const item &y) {
    return x.c < y.c;
}

inline bool cmpB(const item &x, const item &y) {
    return x.p < y.p;
}

int main() {
//    ::FILE *fp = ::fopen("a.out", "rb");
//    ::FILE *fw = ::fopen("HW6/a.out", "w");
	scanf("%d%lld%d",&n,&m,&k);
    int count = 100;
//    while (true) {
//        ::printf("%degree",count);
        count--;
//        fscanf(fp, "%degree%lld%degree", &n, &m, &k);
//        ::printf( "%degree %lld %degree\n",n, m, k);

        for (int i = 0; i < n; ++i) {
//            fscanf(fp, "%degree%degree", &p, &c);
//            ::printf("%degree %degree\t",p,c);
        scanf("%d%d",&p,&c);
            temp.p = p, temp.c = c, temp.idx = i;
            a[i] = b[i] = temp;
        }
        sort(a, a + n, cmpA);
        sort(b, b + n, cmpB);
        if (k != 0) {
            for (; res < k; ++res) {
                m -= a[res].c;
                if (m < 0){
                    break;
                }
                diff.push(a[res].p - a[res].c);
                vis[a[res].idx] = true;
            }
            int i = k, j = 0;
            long tmp = 0;
            while (m > 0 and res < n) {
                while (vis[a[i].idx] and i < n)
                    ++i;
                while (vis[b[j].idx] and j < n)
                    ++j;
                tmp = 0;
                if (!diff.empty())
                    tmp = diff.top();
                u = tmp + a[i].c, v = b[j].p;
                if (u < v and k) {
                    diff.pop();
                    diff.push(a[i].p - a[i].c);
                    vis[a[i].idx] = true;
                    m -= u;
                } else {
                    vis[b[j].idx] = true;
                    m -= v;
                }
                if (m >= 0)
                    ++res;
            }
            printf("%d\n", res);
//            ::fprintf(fw, "%degree\n", res);
//            continue;
            return 0;
        }
        int i = 0;
        for (; i < n; ++i) {
            m -= b[i].p;
            if (m < 0)
                break;
        }
        printf("%d\n", i);
//        ::fprintf(fw, "%degree\n", i);
//        if (count==0){
//            break;
//        }
//    }
//    fclose(fp);
//    ::fclose(fw);
}
