import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Planet {
    int number;
    int x;
    int y;
    int z;

    public Planet(int number, int x, int y, int z) {
        this.number = number;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int compareX(Planet p) {
        return x - p.x;
    }

    public int compareY(Planet p) {
        return y - p.y;
    }

    public int compareZ(Planet p) {
        return z - p.z;
    }
}

class Tunnel {
    private static int[] parents = IntStream.range(0, 100000).toArray();
    long cost;
    int firstPlanet;
    int secondPlanet;

    public Tunnel(int p1, int p2, int cost) {
        this.firstPlanet = p1;
        this.secondPlanet = p2;
        this.cost = cost;
    }

    private int getParent(int number) {
        int result = number;
        while (result != parents[result])
            result = parents[result];
        return result;
    }

    public boolean establishNewLink() {
        firstPlanet = getParent(firstPlanet);
        secondPlanet = getParent(secondPlanet);

        if (firstPlanet == secondPlanet)
            return false;

        if (firstPlanet < secondPlanet)
            parents[secondPlanet] = firstPlanet;
        else
            parents[firstPlanet] = secondPlanet;
        return true;
    }

    public int compareTo(Tunnel o) {
        if (cost >= o.cost)
            return 1;
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        new Main().problem4();
    }

    public void problem4() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int count = 0;
        long totalCost = 0;
        List<Planet> planets = IntStream.range(0, n)
                .mapToObj(index -> new Planet(index, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()))
                .collect(Collectors.toList());

        List<Tunnel> tunnels = new ArrayList<>();

        planets.sort(Planet::compareX);
        for (int i = 0; i < n - 1; i ++) {
            Planet a = planets.get(i);
            Planet b = planets.get(i + 1);
            tunnels.add(new Tunnel(a.number, b.number, Math.abs(a.x - b.x)));
        }
        planets.sort(Planet::compareY);
        for (int i = 0; i < n - 1; i ++) {
            Planet a = planets.get(i);
            Planet b = planets.get(i + 1);
            tunnels.add(new Tunnel(a.number, b.number, Math.abs(a.y - b.y)));
        }
        planets.sort(Planet::compareZ);
        for (int i = 0; i < n - 1; i ++) {
            Planet a = planets.get(i);
            Planet b = planets.get(i + 1);
            tunnels.add(new Tunnel(a.number, b.number, Math.abs(a.z - b.z)));
        }
        tunnels.sort(Tunnel::compareTo);

        for (Tunnel tunnel : tunnels) {
            if (!tunnel.establishNewLink())
                continue;
            count++;
            totalCost += tunnel.cost;
            if (count == n - 1)
                break;
        }

        System.out.println(totalCost);
    }
}