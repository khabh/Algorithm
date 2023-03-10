#include <iostream>
using namespace std;

int main() {
	int n, y = 0, m = 0, phone;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> phone;
		y += (phone / 30 + 1) * 10;
		m += (phone / 60 + 1) * 15;
	}	
	if (y < m)
		cout << "Y " << y; 
	else if (m < y)
		cout << "M " << m;
	else
		cout << "Y M " << y;
} 
