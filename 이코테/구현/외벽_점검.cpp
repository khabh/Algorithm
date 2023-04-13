#include <vector>
#include <algorithm>

using namespace std;
bool visit[9];
int dist_count, weak_count, result;
vector<int> friends;

void calc_needed_dist(vector<int> &weak) {
	for (int i = 0; i < weak_count; i++) {
		bool has_result = true;
		int count = 1, position = weak[i] + friends[0];
		for (int j = i; j < i + weak_count; j++) {
			if (position < weak[j]) {
				count++;
				if (count > dist_count || count > result) {
					has_result = false;
					break;
				}
				position = weak[j] + friends[count - 1];
			}
		}
		if (has_result) {
			result = min(result, count);
		}
	}
}

void dfs(vector<int> &dist, vector<int> &weak) {
	if (friends.size() == dist_count) {
		calc_needed_dist(weak);
        return;
	}
	for (int i = 0; i < dist_count; i++) {
		if (visit[i])
			continue;
		friends.push_back(dist[i]);
		visit[i] = true;
		dfs(dist, weak);
		friends.pop_back();
		visit[i] = false;	
	}
}

int solution(int n, vector<int> weak, vector<int> dist) { 
	weak_count = weak.size();
	dist_count = dist.size();
	result = dist_count + 1;
	for (int i = 0; i < weak_count; i++) {
		weak.push_back(weak[i] + n);
	}
	
	dfs(dist, weak);
    if (result > dist_count)
        return -1;
	return result;
}

//int linked[202];
//
//int solution(int n, vector<int> weak, vector<int> dist) {
//	int count = weak.size(), alone = 0;
//	priority_queue<tuple<int, int, int>> d;
//	if (weak.size() == 1)
//		return 1;
//	for (int i : weak) 
//		linked[i] = 2;
//	for (int i = 0; i < count - 1; i++) {
//		d.push(tuple<int, int, int>(weak[i + 1] - weak[i], weak[i], weak[i + 1]));
//	}
//	d.push(tuple<int, int, int>(weak[0] - weak[count - 1] + n, weak[count - 1], weak[0]));
//	while (!d.empty()) {
//		int distance, left, right;
//		tie(distance, left, right) = d.top();
//		cout << distance << " " << left << " " << right << "\n";
//		d.pop();
//		linked[left] -= 1;
//		linked[right] -= 1;
//		if (!linked[left])
//			alone++;
//		if (!linked[right])
//			alone++;
//		n -= distance;
//		int sum = 0, need = 0;
//		for (int i = dist.size() - 1; i >= 0; i--) {
//			sum += dist[i];
//			need++;
//			if (sum >= n)
//				break;
//		}
//		if (sum < n)
//			continue;
//		if (alone) {
//			need += alone;
//			if (need > dist.size())
//				continue;
//		}
//		return need;
//	}
//	
//	return -1;
//}

int main() {
	solution(12, {1, 5, 6, 10}, {1, 2, 3, 4});
}