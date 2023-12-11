#include <iostream>
#include <random>
#include <map>
using namespace std;
#define pii pair<int, int>

constexpr int MIN = 1;
constexpr int MAX = 1e9;

int u,v;
random_device rd;
default_random_engine eng(rd());
int N, M;
void generate()
{
    uniform_int_distribution<int> generate_N(1, 1e5+1);
    uniform_int_distribution<int> generate_M(1, 1e5+1);
    N = generate_N(eng);
    N = 1e5;
    cout << N << endl;

    for (int i = 0; i < N; ++i) {
        M=generate_M(eng);
        cout << M << ' ';
    }
    cout << endl;
}
int main () {

    generate();

}