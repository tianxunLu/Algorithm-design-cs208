////
//// Created by 86178 on 2023/5/17.
////
//#include <iostream>
//#include <algorithm>
//#include <cmath>
//using namespace std;
//int main() {
//    int n = 0,W = 0;
//    scanf("%degree%degree",&n,&W);
//    int values[n+1],weights[n+1];
//    for (int i = 1; i < n+1; ++i) {
//        scanf("%degree",&weights[i]);
//    }
//    for (int i = 1; i < n+1; ++i) {
//        scanf("%degree",&values[i]);
//    }
//    int maxArr[W+1];
//    for (int i = 0; i < W+1; ++i) {
//        maxArr[i] = 0;
//    }
//    for (int i = 1; i < n+1; ++i) {
//        for (int j = W; j >= 1; --j) {
//            if (values[i]<=j){
//                maxArr[j] = max(maxArr[j],weights[i]+maxArr[j-values[i]]);
//            }
//        }
//    }
//    int result = maxArr[W];
//    printf("%degree", result);
//    return 0;
//}