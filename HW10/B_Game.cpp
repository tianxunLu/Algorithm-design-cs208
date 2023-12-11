//
// Created by 86178 on 2023/5/17.
//
#include <iostream>
#include <algorithm>
#include <cmath>
#include <cstring>
using namespace std;
std::vector<std::vector<int>> matrix;
std::vector<char> str;
int len;

//3--alice 1--bob 2--draw
void createTwo(){
    for (int i = 0; i < len-1; ++i) {
        if (str[i]!=str[i+1]){
            matrix[i][i+1] = 3;
        }else{
            matrix[i][i+1] = 2;
        }
    }
}
int whenEqual(int length,int start,int peoplePos,bool isalice){
    char people1 = str[peoplePos];
    if (length==2){
        if (str[start]==str[1+start]){
            return 2;
        }else{
            return 3;
        }
    }
    char people2;
    int whenequal;
    int end = start+length-1;
    if (peoplePos == start){
        if (str[start+1] < people1){ //b axxxb
            people2 = str[start+1];
            if (whenequal<1){
                whenequal = 1;
            }
        }else if(str[start+1] > people1){ //a bxxxa
            people2 = people1;
            if(whenequal < matrix[start + 1][end-1]){
                whenequal = matrix[start + 1][end-1];
            }
        }else { //a axxxa
            people2 = str[peoplePos + 1]; //people1 choose the front one
            int front = whenEqual(length-1,start+1,start+1,0); //substr[i+1,i+step-1] back=people1
            people2 = str[end];
            int back = whenEqual(length-1,start+1,end,0); //substr[i,i+step-2] front=people1
            if (whenequal<max(front,back)) {
                whenequal = max(front,back);
            }
        }
    }else{
        //没写完呢
        if (str[end-1] < people1){ // bxxxa b
            people2 = str[end-1];
            if (whenequal<1){
                whenequal = 1;
            }
        }else if(str[start+1] > people1){ //a bxxxa
            people2 = people1;
            if(whenequal < matrix[start + 1][end-1]){
                whenequal = matrix[start + 1][end-1];
            }
        }else { //a axxxa
            people2 = str[peoplePos + 1]; //people1 choose the front one
            int front = whenEqual(length-1,start+1,start+1,1); //substr[i+1,i+step-1] back=people1
            people2 = str[end];
            int back = whenEqual(length-1,start+1,end,1); //substr[i,i+step-2] front=people1
            if (whenequal<max(front,back)) {
                whenequal = max(front,back);
            }
        }
    }
    return whenequal;
}
void createMatrix(int step){
    if (len<4){
        return;
    }
    for (int i = 0; i < len-step+1; ++i) {
        char s1 = str[i];
        int end = i+step-1; //end is also the place we store the value
        char s2 = str[end];
        if (s1 != s2) {
            matrix[i][end] = 3; //alice
        } else {
//            matrix[i][i+step-1] = equal(i,i+step-1,step);
            char alice = s1; //alice choose the front one
            char bob; //打算中途易辙 这里分四种情况讨论
            if (s1>str[i+1]){
                bob = str[i+1];
                return;
            }
            int front = whenEqual(step-1,i+1,i,1); //substr[i+1,i+step-1] back=alice
            alice = s2;
            int back = whenEqual(step-1,i,end,1); //substr[i,i+step-2] front=alice
            matrix[i][end] = max(front,back);
        }
    }
}
int main() {
    int n = 0; //testcases
    scanf("%d",&n);
    for (int i = 0; i < n; ++i) {
        string s;
        std::cin >> s;
        std::vector<char> temp(s.begin(), s.end()); //char array
        str = temp;
        len = str.size();
        matrix.assign(len, std::vector<int>(len, 0)); //build empty matrix
        createTwo(); //initialize the two by two situation--which is the basic situation
        for (int j = 2; j <= len; j=j+2) {
            createMatrix(j); //build the whole matrix
        }
        int result = matrix[0][len-1];
        if (result==3) {
            std::printf("Alice\n");
        } else if (result==1) {
            std::printf("Bob\n");
        } else {
            std::printf("Draw\n");
        }
    }
    return 0;
}