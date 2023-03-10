#include <iostream>
#include <algorithm>
using namespace std; 
void print_result(int a, int b);

int num[9];
int total = 0;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	for (int i = 0; i < 9; i++) {
		cin >> num[i];
		total += num[i];
	}
	sort(num, num + 9);
	
	for (int i = 0; i < 8; i++) {
		int temp = num[i];
		for (int j = i + 1; j < 9; j++) {
			temp += num[j];
			if (total - temp == 100) {
				print_result(i, j);
				return 0;
			}
			temp -= num[j];
		}
	}
} 

void print_result(int a, int b) {
	for (int i = 0; i < 9; i++) {
		if (i == a || i == b) continue;
		cout << num[i] << "\n";
	}
}
