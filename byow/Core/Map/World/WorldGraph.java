package byow.Core.Map.World;

import byow.Core.Map.Room;

import java.util.*;

public class WorldGraph {
	private Map<Long, RNode> rNodeMap = new HashMap<>();
	private Map<Long, Set<RNode>> neighbors = new HashMap<>();


	/** Create a WorldGraph. */
	public WorldGraph(List<Room> roomList) {
		long curId = 1;
		for (Room r: roomList) {
			RNode curNode = new RNode(r);
			rNodeMap.put(curId, curNode);
			curId++;
		}
		// add RNode to neighbors
		for(Long id : rNodeMap.keySet()) {
			int dist = rNodeMap.get(id).getRoom().getWidth() + rNodeMap.get(id).getRoom().getHeight();
			neighbors.put(id, connectNeighbors(id, dist));
		}
	}

	/**
	 * Connect RNode within distance as neighbors.
	 * @param v id
	 * @param dist scope
	 * @return neighbors' set
	 */
	private Set<RNode> connectNeighbors(Long v, int dist) {
		Set<RNode> rNodeSet = new HashSet<>();
		for(Long id : rNodeMap.keySet()) {
			if (v != id) {
				if (estimateDistance(v, id) < dist) {
					rNodeSet.add(rNodeMap.get(id));
				}
			}
		}
		Long[] keySet = rNodeMap.keySet().toArray(new Long[0]);
		if (rNodeSet.size() == 0) {
			rNodeSet.add(rNodeMap.get(keySet[0]));
		}
		return rNodeSet;

	}

	private double estimateDistance(Long start, Long goal) {
		RNode sRode = rNodeMap.get(start);
		RNode goalRode = rNodeMap.get(goal);
		int distX = sRode.getRoom().getCenter().getX() - goalRode.getRoom().getCenter().getX();
		int distY = sRode.getRoom().getCenter().getY() - goalRode.getRoom().getCenter().getY();
		double estDist = Math.sqrt(distX*distX + distY*distY);
		return estDist;

	}


	public Map<Long, RNode> getRodeMap() {
		return rNodeMap;
	}

	public Map<Long, Set<RNode>> getNeighbors() {
		return neighbors;
	}
}
