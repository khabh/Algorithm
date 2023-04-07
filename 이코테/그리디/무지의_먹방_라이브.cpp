#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<int> times, long long k) {
    vector<int> result_foods;
	long long sum = 0, prev = 0, round_time, food_count = times.size(), remain;
	remain = food_count;
	priority_queue<pair<int, int>> foods;

	for (int i = 0; i < food_count; i++) {
		foods.push({-times[i], i});
		sum += times[i];
	}	
	
	if (sum <= k) {
		return -1;
	} 
    
	while ((round_time = (-foods.top().first - prev) * remain) <= k) {
		k -= round_time;
		remain--;
		prev = -foods.top().first;
		foods.pop();
	}
	
	while (!foods.empty()) {
		result_foods.push_back(foods.top().second);
		foods.pop();
	}
	
	sort(result_foods.begin(), result_foods.end());
	return result_foods[k % remain] + 1;
}

int main() {
    return solution({ 3,1,2 }, 5);
}