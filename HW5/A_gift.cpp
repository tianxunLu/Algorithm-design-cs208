#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int maina(){
    int n = 0; //gifts
    int m = 0; //coins
    scanf("%d%d",&n,&m);
    //define
    long long sum = 0;
    priority_queue<long, vector<long>, greater<long>> min_heap;
    int weighs[n];
    int prices[n];
    for (int i = 0; i < n; ++i) {
        int price = 0;
        scanf("%d",&price);
        int div = price/100;
        int leave = price-div*100;
        prices[i] = leave;
    }
    for (int i = 0; i < n; ++i) {
        int weigh = 0;
        scanf("%d",&weigh);
        weighs[i] = weigh;
    }
    //operation
    for (int i = 0; i < n; ++i) {
        //add
        if (prices[i]){
            long value = (long)weighs[i]*(long)(100-prices[i]);
            min_heap.push(value);
        }
        if (prices[i]<=m){
            m = m-prices[i];
        }else{
            m = m+100-prices[i];
            //pick up
            sum+=min_heap.top();
            min_heap.pop();
        }
    }
    //output
    printf("%lld\n",sum);
    return 0;
}
