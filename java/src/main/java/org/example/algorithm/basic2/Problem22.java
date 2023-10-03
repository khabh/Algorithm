package org.example.algorithm.basic2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Member {
    int order;
    boolean visited = false;
    List<Member> friends = new ArrayList<>();

    public Member(int order) {
        this.order = order;
    }

    private void addFriend(Member member) {
        friends.add(member);
    }

    public void checkAsVisited() {
        visited = true;
    }

    public void uncheckVisited() {
        visited = false;
    }

    public boolean canVisit() {
        return !visited;
    }

    public List<Member> getFriends() {
        return friends;
    }

    public static void addRelationBetween(Member firstMember, Member secondMember) {
        firstMember.addFriend(secondMember);
        secondMember.addFriend(firstMember);
    }
}

public class Problem22 {
    private static boolean dfs(Member member, int count) {
        if (count == 5) {
            return true;
        }
        member.checkAsVisited();
        return member.getFriends()
                .stream()
                .filter(Member::canVisit)
                .anyMatch(friend -> {
                    if (dfs(friend, count + 1))
                        return true;
                    friend.uncheckVisited();
                    return false;
                });
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            members.add(new Member(i));
        }
        while (m-- > 0) {
            Member a = members.get(scanner.nextInt());
            Member b = members.get(scanner.nextInt());
            Member.addRelationBetween(a, b);
        }

        String result = "0";

        for (Member member : members) {
            if (member.canVisit() && dfs(member, 1)) {
                result = "1";
                break;
            }
            member.uncheckVisited();
        }

        System.out.println(result);
    }
}
