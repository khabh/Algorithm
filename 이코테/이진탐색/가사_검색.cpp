#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;
unordered_map<string, int> cache;
vector<int> answer;

vector<int> solution(vector<string> words, vector<string> queries) {
    map<int, vector<string>> word_by_len;
    for (string word : words) {
        word_by_len[word.length()].push_back(word);
    }
    for (string query : queries) {
        int start = 0, end = query.length() - 1, count = 0, gap;
        if (query[0] == query[end] && query[0] == '?') {
            answer.push_back(word_by_len[query.length()].size());
            continue;
        }
        if (cache.find(query) != cache.end()) {
            answer.push_back(cache[query]);
            continue;
        }
        if (query[0] != '?')
            end = query.find('?') - 1;
        else {
            string temp = query;
            reverse(temp.begin(), temp.end());
            start = query.length() - temp.find('?');
        }
            
        for (string word : word_by_len[query.length()]) {
            bool match = true;
            for (int i = start; i <= end; i++) {
                if (word[i] != query[i]) {
                    match = false;
                    break;
                }
            }
            if (match)
                count++;
        }
        answer.push_back(count);
        cache[query] = count;
    }
    return answer;
}