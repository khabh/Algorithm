#include <iostream>
#include <vector>

using namespace std;
vector<vector<int>> new_lock;
int count = 0;

void rotate_key(vector<vector<int>> &key)
{
	int m = key.size();
    vector<vector<int>> temp = key;
    for (int i = 0; i < m; i++)
    {
        for (int j = 0; j < m; j++)
        {
            temp[i][j] = key[m - 1 - j][i];
        }
    }
    key = temp;
}

bool try_match(vector<vector<int>> &key, int x, int y) {
	int a = 0, b = 0, temp = 0, key_size = key.size();
	for (int i = x; i < x + key_size; i++, a++) {
		for (int j = y; j < y + key_size; j++, b++) {
			if (new_lock[i][j] == key[a][b])
				return false;
			if (!new_lock[i][j] && key[a][b]) {
				temp++;
				if (temp > count) 
					return false;
			}
		}
		b = 0;
	}
	
	return temp == count;
}



bool solution(vector<vector<int>> key, vector<vector<int>> lock) {
	int lock_size = lock.size(), key_size = key.size(), x = 0, y;
	int map_size = lock_size + 2 * (key_size - 1);
	new_lock.resize(map_size, vector<int>(map_size, 2));
	
	int max_index = map_size - (key_size - 1);
	for (int i = key_size - 1; i < max_index; i++, x++) {
		y = 0;
		for (int j = key_size - 1; j < max_index; j++, y++) {
			new_lock[i][j] = lock[x][y];
			if (!lock[x][y])
				count++;
		}
	}
	
	for (int k = 0; k < 4; k++) {
		for (int i = 0; i < max_index; i++) {
			for (int j = 0; j < max_index; j++) {
				if (try_match(key, i, j))
					return true;
			}
		}
		if (k == 3)
			break;
		rotate_key(key);
	}
	
	return false;
}

int main() {
	cout << solution({{0, 0, 0}, {1, 0, 0}, {0, 1, 1}}, {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
}