package ru.university.hits.code.module_1;

class K_сочетания {

	
	/*
	 
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void combine(vector<vector<int>>& lst, int idx, int n, int k, vector<int>& res) {
    if (res.size() == k) {
        for (int x : res)
            cout << x << " ";
        cout << endl;
        return;
    }
    if (idx >= n)
        return;
    res.push_back(lst[idx][0]);
    combine(lst, idx + 1, n, k, res);
    res.pop_back();
    combine(lst, lst[idx][1], n, k, res);
}

void merge(vector<int>& array, vector<int>& workSpace, int lowPtr, int highPtr, int upperBound) {
    int j = 0;
    int lowerBound = lowPtr;
    int mid = highPtr - 1;
    int n = upperBound - lowerBound + 1;
    while (lowPtr <= mid && highPtr <= upperBound) {
        if (array[lowPtr] < array[highPtr])
            workSpace[j++] = array[lowPtr++];
        else
            workSpace[j++] = array[highPtr++];
    }
    while (lowPtr <= mid) {
        workSpace[j++] = array[lowPtr++];
    }
    while (highPtr <= upperBound) {
        workSpace[j++] = array[highPtr++];
    }
    for (j = 0; j < n; j++) {
        array[lowerBound + j] = workSpace[j];
    }
}

void heapSort(vector<int> & array, vector<int>& workSpace, int lowerBound, int upperBound) {
    if (lowerBound == upperBound)
        return;
    else {
        int mid = (lowerBound + upperBound) / 2;
        heapSort(array, workSpace, lowerBound, mid);
        heapSort(array, workSpace, mid + 1, upperBound);
        merge(array, workSpace, lowerBound, mid + 1, upperBound);
    }
}

int main() {
    int n;
    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    int k;
    cin >> k;
    vector<int> w(n);
    heapSort(a, w, 0, n - 1);
    vector<vector<int>> b;
    b.push_back({ a[n - 1], n });
    int next = n;
    for (int i = n - 2; i >= 0; i--) {
        if (a[i] != a[i + 1])
            next = i + 1;
        b.insert(b.begin(), { a[i], next });
    }
    vector<int> res;
    combine(b, 0, n, k, res);
    return 0;
}

	
	
	
	
	
	
	 */
	
}