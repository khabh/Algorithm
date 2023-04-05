#include <iostream>

using namespace std;

int main() {
	int numbers[10] = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
	
	for (int i = 0; i < 10; i++) {
		for (int j = i; j > 0; j--) {
			if (numbers[j] < numbers[j - 1]) {
				int temp = numbers[j];
				numbers[j] = numbers[j - 1];
				numbers[j - 1] = temp;
			}
			else 
				break;
		}
	}
	
	for (int number : numbers) {
		cout << number << " ";
	}
}