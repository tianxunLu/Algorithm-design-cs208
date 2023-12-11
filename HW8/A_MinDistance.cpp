#include <iostream>
#include <algorithm>
#include<stdio.h>

using namespace std;
struct point{
    int x;
    int y;
};
long long distance(point a, point b){
    long long dis = 0;
    dis = (long long)(a.x-b.x)*(a.x-b.x)+(long long)(a.y-b.y)*(a.y-b.y);
    return dis;
}
bool comparex(point a, point b){
    return a.x<b.x; //by x
}
bool comparey(point a, point b){
    return a.y<b.y; //by y
}
point trans[2];
long long close(point arr[],int s,int b){
    long long d0,d1,d2,d3;
    point sub[b - s + 1],zuo[2],you[2];
    if (b-s==1){
        //directly return
        trans[0].x = arr[s].x;
        trans[0].y = arr[s].y;
        trans[1].x = arr[b].x;
        trans[1].y = arr[b].y;
        return distance(arr[s],arr[b]);
    }
    if (b-s==2) {
        d1 = distance(arr[s], arr[s+1]);
        d2 = distance(arr[s+1], arr[b]);
        d3 = distance(arr[s], arr[b]);
        //find the minimum
        if ((d1 < d2) && (d1 < d3)) {
            trans[0].x = arr[s].x;
            trans[0].y = arr[s].y;
            trans[1].x = arr[s + 1].x;
            trans[1].y = arr[s + 1].y;
            return d1;
        } else if (d2 < d3) {
            trans[0].x = arr[s + 1].x;
            trans[0].y = arr[s + 1].y;
            trans[1].x = arr[b].x;
            trans[1].y = arr[b].y;
            return d2;
        } else {
            trans[0].x = arr[s].x;
            trans[0].y = arr[s].y;
            trans[1].x = arr[b].x;
            trans[1].y = arr[b].y;
            return d3;
        }
    }
    int mid = (s+b)/2;
    d1 = close(arr,s,mid);
    zuo[0]=trans[0];
    zuo[1]=trans[1];
    d2=close(arr,mid+1,b);
    you[0]=trans[0];
    you[1]=trans[1];
    //find the relative small one
    if(d1<d2){
        d0=d1;
        trans[0]=zuo[0];
        trans[1]=zuo[1];
    }else{
        d0=d2;
        trans[0]=you[0];
        trans[1]=you[1];
    }
    int index=0;
    int i,j;
    //record arr[mid,mid+degree]
    for(i = mid+1;(i <= b) && ((arr[i].x-arr[mid].x) < d0); i++){
        sub[index++]=arr[i];
    }
    //record arr[mid-degree,mid]
    for(i = mid;(i >= s) && ((arr[mid].x-arr[i].x) < d0); i--){
        sub[index++]=arr[i];
    }
    //compare by y
    sort(sub, sub + index, comparey);
    //find min close in arr[mid-degree,mid+degree]
    for(i = 0;i < index;i++){
        for(j = i+1;j < i+500 && j < index;j++){
            if((sub[j].y - sub[i].y) >= d0){
                break;
            }else{
                d3 = distance(sub[i], sub[j]);
                if(d3 < d0){
                    trans[0].x=sub[i].x;
                    trans[0].y=sub[i].y;
                    trans[1].x=sub[j].x;
                    trans[1].y=sub[j].y;
                    d0=d3;
                }
            }
        }
    }
    return d0;
}

int main() {
    int n = 0;//n<10^5
    //xi,yi < 2*10^7
// note:input
    FILE *fp = fopen("a.in", "rb");
    while(true) {
        int temp = n;
        fscanf(fp, "%degree", &n);
//        if (n==temp){
//            break;
//        }
//    scanf("%degree",&n);
        point arr[n];
        for (int i = 0; i < n; ++i) {
            fscanf(fp, "%degree", &arr[i].x);
            fscanf(fp, "%degree", &arr[i].y);
//        scanf("%degree",&arr[i].x);
//        scanf("%degree",&arr[i].y);
        }
//        ::fclose(fp);
        sort(arr, arr + n, comparex);
        long long result = close(arr, 0, n - 1);

// note:output
        printf("%lld\n", result);
        ::FILE *fw = ::fopen("a.out", "w");
        ::fprintf(fw, "%lld", result);
//        ::fclose(fw);
    }
    return 0;
}
