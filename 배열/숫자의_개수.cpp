#include <iostream>
using namespace std;

 int main() {
 	ios::sync_with_stdio(0);
  	cin.tie(0);
  	
 	int a, b, c;
 	int num[10] = {};
 	
 	cin >> a >> b >> c;
 	string s = to_string(a * b * c);
 	
 	for (auto alpha: s)
 		num[alpha - '0']++;
 	for (int n: num)
 		cout << n << "\n";
 }
