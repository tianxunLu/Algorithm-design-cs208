#include <iostream>
#include <algorithm>
#include <cmath>

int main() {
// note:input
//    FILE *fp = fopen("a.in", "rb");
//    fscanf(fp, "%d", &n);
//    ::fclose(fp);
    //scanf("%d",&n);
    int result;

// note:output
    printf("%d", result);
    ::FILE *fw = ::fopen("a.out", "w");
    ::fprintf(fw, "%d", result);
    ::fclose(fw);
    return 0;
}
