#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct item {
    int origin;
    int discount;
//    int difference;
    int index;

    bool operator<(const item &other) const {
        return discount < other.discount;
    }
};

//origin
inline bool compare1(const item &x, const item &y) {
    return x.origin < y.origin;
}

//discount
inline bool compare2(const item &x, const item &y) {
    return x.discount < y.discount;
}

//compare difference
struct compare {
    bool operator()(const item &item1, const item &item2) const {
        //not sure
        return item1.origin - item1.discount > item2.origin - item2.discount;
    }
};

int main() {
    int n = 0;
    long long m = 0;
    int k = 0;
    scanf("%d%lld%d", &n, &m, &k);
    item ori[n];
    item dis[n];
    bool vis[n];
    for (int i = 0; i < n; ++i) {
        item item;
        scanf("%d%d", &item.origin, &item.discount);
        item.index = i;
//        item.difference = item.origin - item.discount;
        ori[i] = dis[i] = item;
        vis[i] = false;
    }
    sort(ori, ori + n, compare1);
    sort(dis, dis + n, compare2);
    std::priority_queue<item, std::vector<item>, compare> bycoupon;
    int maxItems = 0;
    for (int i = 0; i < k; ++i) {
        long long canbuy = m - (long long) dis[i].discount;
        if (canbuy >= 0) {
            m -= (long long) dis[i].discount;
            maxItems++;
            bycoupon.push(dis[i]);
            vis[dis[i].index] = true;
        }
    }
    if (bycoupon.size() == 0) {
        for (int j = 0; j < n; ++j) {
            m -= ori[j].origin;
            if (m >= 0) {
                maxItems++;
            } else {
                break;
            }
        }
    } else {
        int point1 = 0, point2 = 0;
        while (point1<n && point2<n) {
            if (!vis[ori[point1].index]){
                if (!vis[dis[point2].index]){
                    item top1 = bycoupon.top();
                    int change = top1.origin-top1.discount + dis[point2].discount;
                    int notchange = ori[point1].origin;
                    if (change > notchange) {
                        if (notchange <= m) {
                            maxItems++;
                            m -= (long long) notchange;
                            vis[ori[point1++].index] = true;
                        } else {
                            break;
                        }
                    } else {
                        if (change <= m) {
                            bycoupon.pop();
                            bycoupon.push(dis[point2]);
                            maxItems++;
                            m -= (long long) change;
                            vis[dis[point2++].index] = true;
                        } else {
                            break;
                        }
                    }
                } else{
                    point2++;
                }
            } else{
                point1++;
            }
        }
    }
    printf("%d", maxItems);
    return 0;
}
