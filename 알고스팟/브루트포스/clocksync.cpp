#include <iostream>
#include <algorithm>
#define NO_RESULT 9999

using namespace std;
string switches[10] = {
	"1110000000000000",
	"0001000101010000",
	"0000100000100011",
	"1000111100000000",
	"0000001110101000",
	"1010000000000011",
	"0001000000000011",
	"0000110100000011",
	"0111110000000000",
	"0001110001000100"
};
int clocks[16], result;

bool allAlign() {
	for (int i : clocks) {
		if (i != 12)
			return false; 
	}
	return true;
}

void push(int type) {
	for (int j = 0; j < 16; j++) {
		if (switches[type][j] != '1')
			continue;
		clocks[j] += 3;
		if (clocks[j] == 15)
			clocks[j] = 3;
	}
}

void turn_off(int type) {
	for (int j = 0; j < 16; j++) {
		if (switches[type][j] != '1')
			continue;
		clocks[j] -= 3;
		if (clocks[j] == 0)
			clocks[j] = 12;
	}
}

void use_switch(int type, int count) {
	if (allAlign()) {
		result = min(count, result);
		return;
	}
	if (type == 10) {
		return;
	}
	if (count < result)
		use_switch(type + 1, count);

	for (int i = 0; i < 3; i++) {
		push(type);
		count++;
		use_switch(type + 1, count);
		if (count > result)
			return;
	}
	for (int j = 0; j < 3; j++) {
		turn_off(type);
	}
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
	int c;
	cin >> c;
	while (c--) {
		result = NO_RESULT;
		for (int i = 0; i < 16; i++)
			cin >> clocks[i];
		use_switch(0, 0);
		if (result == NO_RESULT)
			cout << "-1\n";
		else 
			cout << result << "\n";
	}
}