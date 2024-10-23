package ru.university.hits.code.module_1;

import java.util.List;
import java.util.Scanner;

/*
#include <iostream>
#include <vector>

using namespace std;

vector<vector<bool>> initArray() {
    vector<vector<bool>> field(3, vector<bool>(3));
    for (int i = 0; i < field.size(); i++) {
        for (int j = 0; j < field[0].size(); j++) {
            int input;
            cin >> input;
            field[i][j] = input == 1;
        }
    }
    return field;
}

int main() {
    int count = 0;
    vector<vector<bool>> field1 = initArray();
    vector<vector<bool>> field2 = initArray();
    vector<vector<bool>> field3 = initArray();

    vector<vector<vector<bool>>> fields = {field1, field2, field3};

    for (const auto& field : fields) {
        // 1
        if (field[0][0] && field[0][1] && field[0][2]) count++;
        // 2
        if (field[1][0] && field[1][1] && field[1][2]) count++;
        // 3
        if (field[2][0] && field[2][1] && field[2][2]) count++;
        // 4
        if (field[0][0] && field[1][0] && field[2][0]) count++;
        // 5
        if (field[0][1] && field[1][1] && field[2][1]) count++;
        // 6
        if (field[0][2] && field[1][2] && field[2][2]) count++;
        // 7
        if (field[0][0] && field[1][1] && field[2][2]) count++;
        // 8
        if (field[2][0] && field[1][1] && field[0][2]) count++;
    }

    // В столбце
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (field3[i][j] && field2[i][j] && field1[i][j]) count++;
        }
    }

    // Первый уровень
    // 1
    if (field1[0][0] && field2[0][1] && field3[0][2]) count++;
    // 2
    if (field1[1][0] && field2[1][1] && field3[1][2]) count++;
    // 3
    if (field1[2][0] && field2[2][1] && field3[2][2]) count++;
    // 4
    if (field1[0][0] && field2[1][0] && field3[2][0]) count++;
    // 5
    if (field1[0][1] && field2[1][1] && field3[2][1]) count++;
    // 6
    if (field1[0][2] && field2[1][2] && field3[2][2]) count++;
    // 7
    if (field1[0][0] && field2[1][1] && field3[2][2]) count++;
    // 8
    if (field1[2][0] && field2[1][1] && field3[0][2]) count++;

    // Последний уровень
    // 1
    if (field3[0][0] && field2[0][1] && field1[0][2]) count++;
    // 2
    if (field3[1][0] && field2[1][1] && field1[1][2]) count++;
    // 3
    if (field3[2][0] && field2[2][1] && field1[2][2]) count++;
    // 4
    if (field3[0][0] && field2[1][0] && field1[2][0]) count++;
    // 5
    if (field3[0][1] && field2[1][1] && field1[2][1]) count++;
    // 6
    if (field3[0][2] && field2[1][2] && field1[2][2]) count++;
    // 7
    if (field3[0][0] && field2[1][1] && field1[2][2]) count++;
    // 8
    if (field3[2][0] && field2[1][1] && field1[0][2]) count++;

    cout << count << endl;

    return 0;
}

 */
class Крестики_Нолики_3Д {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        boolean[][] field1 = intiArray(sc);
        boolean[][] field2 = intiArray(sc);
        boolean[][] field3 = intiArray(sc);

        for (boolean[][] field : List.of(field1, field2, field3)) {
            // 1
            if (field[0][0] && field[0][1] && field[0][2]) count++;
            // 2
            if (field[1][0] && field[1][1] && field[1][2]) count++;
            // 3
            if (field[2][0] && field[2][1] && field[2][2]) count++;
            // 4
            if (field[0][0] && field[1][0] && field[2][0]) count++;
            // 5
            if (field[0][1] && field[1][1] && field[2][1]) count++;
            // 6
            if (field[0][2] && field[1][2] && field[2][2]) count++;
            // 7
            if (field[0][0] && field[1][1] && field[2][2]) count++;
            // 8
            if (field[2][0] && field[1][1] && field[0][2]) count++;
        }

        // In a column
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                if (field3[i][j] && field2[i][j] && field1[i][j]) count++;
            }
        }
        // First level
        // 1
        if (field1[0][0] && field2[0][1] && field3[0][2]) count++;
        // 2
        if (field1[1][0] && field2[1][1] && field3[1][2]) count++;
        // 3
        if (field1[2][0] && field2[2][1] && field3[2][2]) count++;
        // 4
        if (field1[0][0] && field2[1][0] && field3[2][0]) count++;
        // 5
        if (field1[0][1] && field2[1][1] && field3[2][1]) count++;
        // 6
        if (field1[0][2] && field2[1][2] && field3[2][2]) count++;
        // 7
        if (field1[0][0] && field2[1][1] && field3[2][2]) count++;
        // 8
        if (field1[2][0] && field2[1][1] && field3[0][2]) count++;
        // Last level
        // 1
        if (field3[0][0] && field2[0][1] && field1[0][2]) count++;
        // 2
        if (field3[1][0] && field2[1][1] && field1[1][2]) count++;
        // 3
        if (field3[2][0] && field2[2][1] && field1[2][2]) count++;
        // 4
        if (field3[0][0] && field2[1][0] && field1[2][0]) count++;
        // 5
        if (field3[0][1] && field2[1][1] && field1[2][1]) count++;
        // 6
        if (field3[0][2] && field2[1][2] && field1[2][2]) count++;
        // 7
        if (field3[0][0] && field2[1][1] && field1[2][2]) count++;
        // 8
        if (field3[2][0] && field2[1][1] && field1[0][2]) count++;
        System.out.println(count);
    }

    private static boolean[][] intiArray(Scanner sc) {
        boolean[][] field = new boolean[3][3];

        for (int i = 0; i < field.length; i++) {

            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = sc.nextInt() == 1 ? true : false;
            }
        }
        return field;
    }
}