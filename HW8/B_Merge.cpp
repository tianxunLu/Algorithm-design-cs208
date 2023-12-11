#include <iostream>
#include <algorithm>
#include <cmath>
//#include <filesystem>
//yong java
struct node_B{
    int rank;
    int val;
    int left;
};
void merge(node_B* L, int nl, node_B* R, int nr, node_B* A) {
    int i = 0, j = 0, k = 0;
    while (i < nl && j < nr) {
        if (L[i].val <= R[j].val) {
            A[k] = L[i];
            i++;
        }
        else {
            A[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < nl) {
        A[k] = L[i];
        i++;
        k++;
    }
    while (j < nr) {
        A[k] = R[j];
        j++;
        k++;
    }
}

node_B* merge_sort(node_B* A, int n) {
    if (n > 1) {
        int p = n / 2;
        node_B* B = (node_B*)malloc(p * sizeof(int));
        node_B* C = (node_B*)malloc((n - p) * sizeof(int));
        memcpy(B, A, p * sizeof(int));
        memcpy(C, A + p, (n - p) * sizeof(int));
        merge_sort(B, p);
        merge_sort(C, n - p);
        merge(B, p, C, n - p, A);
        free(B);
        free(C);
    }
    return A;
}

int main() {
    int n = 0;//<5*10^4
//    std::filesystem::path path = std::filesystem::current_path();
//    std::cout << "Current working directory: " << path.string() << std::endl;
// note:input
    FILE* fp = fopen("a.in", "rb");
    fscanf(fp, "%d",&n);
    node_B arr[n];
    node_B rearr[n];
    for (int i = 0; i < n; ++i) {
        int ai = 0;
//        scanf("%d",&ai);
        fscanf(fp,"%d",&ai);
        node_B temp;
        temp.val = ai;
        arr[i] = temp;
        rearr[i] = temp;
    }
    ::fclose(fp);
    //scanf("%d",&n);

    merge_sort(rearr,n);
    for (int i = 0; i < n; ++i) {

    }
    int result;

// note:output
    printf("%d",result);
    ::FILE* fw = ::fopen("a.out","w");
    ::fprintf(fw,"%d",result);
    ::fclose(fw);
    return 0;
}