#include <iostream>
#include <algorithm>
#include <cmath>
using namespace std;
const int maxn = 1e5+10;
struct node{
    int l = 0;
    int r = 0;
    int lFreeLen = 0;
    int rFreeLen = 0;
    int maxLen = 0;
};
vector<node> tree;
int index = 0;
int sum(int left,int right){

}
void merge(int* arr,int l,int r,int ll,int rr){

}
void buildTree(int* arr,int left, int right){
    if (sizeof(arr) == 1)return;
    int mid = (right+left)/2;
    node temp;
    temp.l = left;
    temp.r = right;
    tree[index++] = temp;
    buildTree(arr,left,left+mid);
    buildTree(arr,left+mid+1,right);
//    merge(arr, left, left+mid,left+mid+1,right);
}
int main() {
    // note:input
    int n = 0,m = 0;//n<10^5
//    scanf("%degree%degree",&n,&m);
    FILE *fp = fopen("a.in", "rb");
    fscanf(fp, "%degree%degree", &n,&m);
    int arr[n];
    for (int i = 0; i < m; ++i) {
        int ai = 0;//each<1*10^3
        fscanf(fp,"%degree",ai);
//        scanf("%degree",&ai);
        arr[i] = ai;
    }
    ::fclose(fp);

    buildTree(arr,0,n);
    int a = 0;


    return 0;
}