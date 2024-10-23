package ru.university.hits.code.module_1;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

/*
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int n;
    cin >> n;
    vector<int> array(n);

    for (int i = 0; i < n; i++) {
        cin >> array[i];
    }

    vector<int> length(n);
    vector<int> lengthIndexes(n);
    vector<int> positions(n);
    int lis = 0;
    int maxLis = 0;

    for (int i = 0; i < n; i++) {
        int pos = lower_bound(length.begin(), length.begin() + lis, array[i]) - length.begin();

        length[pos] = array[i];
        lengthIndexes[pos] = i;
        positions[i] = (pos > 0) ? lengthIndexes[pos - 1] : -1;

        if (pos + 1 > lis) {
            lis = pos + 1;
            maxLis = max(maxLis, lis);
        }
    }

    cout << (maxLis == 1 ? -1 : maxLis) << endl;

    return 0;
}

 */
class Наибольшая_возрастающая_подпоследовательность {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }
        int[] length = new int[n];
        int[] lengthIndexes = new int[n];
        int[] positions = new int[n];
        int lis = 0;
        int maxLis = 0;

        for (int i = 0; i < n; i++) {
            int pos = Arrays.binarySearch(length, 0, lis, array[i]);

            if (pos < 0) {
                pos = -(pos + 1);
            }
            length[pos] = array[i];
            lengthIndexes[pos] = i;
            positions[i] = pos > 0 ? lengthIndexes[pos - 1] : -1;

            if (pos + 1 > lis) {
                lis = pos + 1;
                maxLis = max(maxLis, lis);
            }
        }
        System.out.println(maxLis == 1 ? -1 : maxLis);
    }
}