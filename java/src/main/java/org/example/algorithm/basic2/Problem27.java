package org.example.algorithm.basic2;

import java.util.*;
import java.util.stream.Collectors;

class RouteMap {

    private final List<Station> stations;

    public RouteMap(List<Station> stations) {
        this.stations = stations;
    }

    public void checkLoopLine() {
        Station startStation = stations.get(0);
        startStation.setStart();
        dfs(startStation).ifPresent(startStation::calcLoopDistance);
    }

    private Optional<Integer> dfs(Station station) {
        for (Station connectedStation : station.connectedStations) {
            if (connectedStation.isVisited()) {
                if (connectedStation.canMakeLoopWith(station))
                    return Optional.of(connectedStation.getVisitOrder());
            }
            else {
                connectedStation.visitNextTo(station);
                Optional<Integer> result = dfs(connectedStation);
                if (result.isPresent()) {
                    connectedStation.calcLoopDistance(result.get());
                    return result;
                }
                connectedStation.setNotVisited();
            }
        }
        return Optional.empty();
    }

    public static RouteMap init() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Station> stations = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stations.add(new Station());
        }

        for (int i = 0; i < n; i++) {
            Station first = stations.get(scanner.nextInt() - 1);
            Station second = stations.get(scanner.nextInt() - 1);
            first.addConnectedStation(second);
            second.addConnectedStation(first);
        }

        return new RouteMap(stations);
    }

    public String getLoopDistances() {
        Queue<Station> visitedStations = new LinkedList<>();
        stations.stream().filter(Station::isVisited).forEach(visitedStations::add);

        while (!visitedStations.isEmpty()) {
            Station currentStation = visitedStations.poll();
            currentStation.addUpdatedStationToVisit(visitedStations);
        }

        return stations.stream().map(station -> Integer.toString(station.getLoopDistance())).collect(Collectors.joining(" "));
    }
}

class Station {

    private static final int NOT_VISITED = -1;
    private static final int START = 1;

    List<Station> connectedStations = new ArrayList<>();
    private int visitOrder = NOT_VISITED;
    private int loopDistance = 0;

    public void addConnectedStation(Station station) {
        connectedStations.add(station);
    }

    public boolean isVisited() {
        return visitOrder != NOT_VISITED;
    }

    public void visitNextTo(Station station) {
        visitOrder = station.visitOrder + 1;
    }

    public void setStart() {
        visitOrder = START;
    }

    public void setNotVisited() {
        visitOrder = NOT_VISITED;
    }

    public boolean canMakeLoopWith(Station station) {
        return station.visitOrder - visitOrder > 1;
    }

    public int getVisitOrder() {
        return visitOrder;
    }

    public void calcLoopDistance(int startVisitCount) {
        loopDistance = Math.max(0, startVisitCount - visitOrder);
    }

    public void setLoopDistance(int loopDistance) {
        this.loopDistance = loopDistance;
        visitOrder = START;
    }

    public void addUpdatedStationToVisit(Queue<Station> visitedStations) {
        connectedStations.stream()
                .filter(station -> !station.isVisited())
                .forEach(station -> {
                    station.setLoopDistance(loopDistance + 1);
                    visitedStations.add(station);
                });
    }

    public int getLoopDistance() {
        return loopDistance;
    }
}

public class Problem27 {
    public static void main(String[] args) {
        RouteMap routeMap = RouteMap.init();
        routeMap.checkLoopLine();
        System.out.println(routeMap.getLoopDistances());
    }
}
