////
//// Created by 86178 on 2023/6/2.
////
//#include <iostream>
//#include <algorithm>
//#include <cmath>
//#include <random>
////
//using namespace std;
//
//int main() {
//    std::random_device rd;
//    std::mt19937 gen(rd());
//    ::FILE *fw = ::fopen("e.txt", "w");
//    ::printf("ok");
//    // 创建分布对象来定义随机数的范围
//    std::uniform_int_distribution<long long> mm(1, 10000);
//    std::uniform_int_distribution<long long> nn(1, 50);
//    std::uniform_int_distribution<long long> original(1, 10);
//    for (int i = 0; i < 10000; ++i) {
//        int n = nn(gen);
//        ::fprintf(fw, "%degree\t", n);
//        ::printf("%degree",n);
//        long long m = mm(gen);
//        ::fprintf(fw, "%lld\t", m);
//        std::uniform_int_distribution<long long> kk(1, n);
//        int k = kk(gen);
//        ::fprintf(fw, "%degree\n", k);
//        for (int j = 0; j < n; ++j) {
//            int ori = original(gen);
//            std::uniform_int_distribution<long long> discount(1, ori);
//            int dis = discount(gen);
//            ::fprintf(fw, "%degree\t %degree\n", ori,dis);
//        }
//    }
//    ::fclose(fw);
//    return 0;
//}