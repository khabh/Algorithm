#include <bits/stdc++.h>
using namespace std;

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int n;
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a[26] = {};
		string first, second;
		
		cin >> first >> second;
		if (first.length() != second.length()) { 
		// ���� ���ǿ� �� ���ڿ� ���̰� �׻� �Ȱ��ٴ� ������ ��� ��� invalid Pointer error �߻� 
			cout << "Impossible\n";
			continue;
		}
		for (int j = 0; j < first.length(); j++) {
			a[first[j] - 'a']++;
			a[second[j] - 'a']--;
		} 
		bool result = false;
		for (int count : a) {
			if (count) {
				cout << "Impossible\n";
				result = true;
				break;
			}
		}
		if (!result) {
			cout << "Possible\n";
		}
	}
}
