#include <iostream>
#include <string.h>

using namespace std;

bool areFriends[10][10];
int n;
int result;

int countPairings(bool taken[10]) {
	int firstFree = -1;
	for (int i = 0; i < n; i++) {
		if (!taken[i]) {
			firstFree = i;
			break;
		}
	}	
	
	if (firstFree == -1)
		return 1;
	int temp = 0;
	
	for (int pairWith = firstFree + 1; pairWith < n; pairWith++) {
		if (!taken[pairWith] && areFriends[pairWith][firstFree]) {
			taken[firstFree] = taken[pairWith] = true;
			temp += countPairings(taken);
			taken[firstFree] = taken[pairWith] = false;
		}
	}
	
	return temp;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	
	int c;
	cin >> c;
	while(c--) {
		result = 0;
		memset(areFriends, false, sizeof(areFriends));
		int m;
		cin >> n >> m;
		while (m--) {
			int a, b;
			cin >> a >> b;
			areFriends[a][b] = areFriends[b][a] = true;
		}
		bool taken[10] = {false};
		cout << countPairings(taken) << "\n";
	}
}
