#include <iostream>
#include <algorithm>
#include <cmath>
const int maxn = 5*1e4;
struct node{
    int l = 0;
    int r = 0;
    int lFreeLen = 0;
    int rFreeLen = 0;
    int maxLen = 0;
    int ocuppyLazy = 0;
    int freeLazy = 0;
}tree[maxn+1];
void initial(int left,int right,int parent){
    if (left != right){
        node temp1;
        temp1.l = left;
        int p = (left+right)/2;
        temp1.r = p;
        temp1.lFreeLen = temp1.rFreeLen = temp1.maxLen = p - left + 1;
        tree[parent*2] = temp1;
        initial(left,p,parent*2);
        node temp2;
        temp2.l = p+1;
        temp2.r = right;
        temp2.lFreeLen = temp2.rFreeLen = temp2.maxLen = right - p;
        tree[parent*2+1] = temp2;
        initial(p+1,right,parent*2+1);
    }
}
void clearOcuppy(){

}
void clearFree(){

}
void checkIn(int num,int pos){
    node temp = tree[pos];
    if (temp.ocuppyLazy){
        clearOcuppy();
    }
    if (temp.freeLazy){
        clearFree();
    }
    if(temp.maxLen>num){
        if (temp.lFreeLen>num){

        }
    }
}
void checkOut(int num1,int num2){

}
int main() {
    // note:input
    int n = 0,m = 0;//n,m<5*10^4
    // scanf("%degree%degree",&n,&m);
    FILE *fp = fopen("a.in", "rb");
    fscanf(fp, "%degree%degree", &n,&m);
    node root;
    root.l = 1;
    root.r = n;
    root.lFreeLen = root.rFreeLen = root.maxLen = n;
    tree[1] = root;
    initial(1,n,1);
    for (int i = 0; i < m; ++i) {
        int op = 0;
        fscanf(fp,"%degree",&op);
//        scanf("%degree",op);
        switch (op) {
            case 1:
                int num;
                fscanf(fp,"%degree",num);
//                scanf("%degree",num);
                checkIn(num,1);
                break;
            case 2:
                int num1,num2;
                fscanf(fp,"%degree%degree",&num1,&num2);
                checkOut(num1,num2);
                break;
        }
    }
    ::fclose(fp);
    return 0;
}