#include <iostream>
#include <string>
using namespace std;
int main(){
    int num = 10;
    int * p1 = NULL, * p2 = NULL; // declaration two pointers, initialized to 0
    p1 = &num; // take the address of num, assign to p1
    p2 = &num; // take the address of num, assign to p2
    *p1 = 20; // assign 20 to num
    *p2 = 30; // assign 30 to num
    *p1 = 10;
    int num1 = 5;
    p1 = &num1;
    cout<<*p1<<" "<<*p2<<endl; //5 10
    cout<<p1<<" "<<p2<<" "<<sizeof(p1); //0x3b62dffac8 0x3b62dffacc
    return 0;
}