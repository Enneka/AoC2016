import java.util.ArrayList;
import java.util.Hashtable;

public class AoC2016_1 {
	
	// input
	private static String input = "R4, R1, L2, R1, L1, L1, R1, L5, R1, R5, L2, R3, L3, L4, R4, R4, R3, L5, L1, R5, R3, L4, R1, R5, L1, R3, L2, R3, R1, L4, L1, R1, L1, L5, R1, L2, R2, L3, L5, R1, R5, L1, R188, L3, R2, R52, R5, L3, R79, L1, R5, R186, R2, R1, L3, L5, L2, R2, R4, R5, R5, L5, L4, R5, R3, L4, R4, L4, L4, R5, L4, L3, L1, L4, R1, R2, L5, R3, L4, R3, L3, L5, R1, R1, L3, R2, R1, R2, R2, L4, R5, R1, R3, R2, L2, L2, L1, R2, L1, L3, R5, R1, R4, R5, R2, R2, R4, R4, R1, L3, R4, L2, R2, R1, R3, L5, R5, R2, R5, L1, R2, R4, L1, R5, L3, L3, R1, L4, R2, L2, R1, L1, R4, R3, L2, L3, R3, L2, R1, L4, R5, L1, R5, L2, L1, L5, L2, L5, L2, L4, L2, R3";
	
	// movement related
	private static int X = 0;
	private static int Y = 0;
	private static int direction = 0;
	
	// results
	private static int distance;
	private static ArrayList<String> crossedPositions = new ArrayList<String>();
	
	// visited positions
	private static Hashtable<String, Boolean> positions = new Hashtable<String, Boolean>();
	
	private static void calc () {

		String[] explodedInput = input.split(", ");
		for (String command : explodedInput) {
			//System.out.println(command);
			char rot = command.charAt(0);
			int mov = Integer.parseInt(command.substring(1));

			travel(rot, mov);
		}
		distance = Math.abs(X) + Math.abs(Y);
	}
	
	private static void travel(char rot, int mov) {
		int oldX = X;
		int oldY = Y;
		switch (rot) {
			case 'R':
				direction += 90;
				break;
			case 'L':
				direction -= 90;
				break;
		}
		
		direction = direction%360;
		if (direction == 0) {
			Y += mov;
		} else if (direction == 90 || direction == -270) {
			X += mov;
		} else if (direction == 180 || direction == -180) {
			Y -= mov;
		} else if (direction == 270 || direction == -90) {
			X -= mov;
		}
		
		
		
		//System.out.println("X : " + oldX + " to " + X);
		//System.out.println("Y : " + oldY + " to " + Y);
		
		for (int rollX = oldX; rollX != X + signOf(X - oldX); rollX += signOf(X - oldX)) {
			//System.out.println("\trollX : " + rollX);
			for (int rollY = oldY; rollY != Y + signOf(Y - oldY); rollY += signOf(Y - oldY)) {
				//System.out.println("\t\trollY : " + rollY);
				if (!(oldX == rollX && oldY == rollY))
					visit(rollX, rollY);
			}
		}
	}
	
	private static void visit (int visitedX, int visitedY) {
		if (positions.containsKey(visitedX + "|" + visitedY)) {
			crossedPositions.add(visitedX + "|" + visitedY);
		} else {
			positions.put(visitedX + "|" + visitedY, true);
		}
	}
	
	private static int signOf (int value) {
		if (value == 0) return 1;
		return Integer.signum(value);
	}

	public static void main(String[] args) {
		AoC2016_1.calc();
		System.out.println("Distance : " + distance);
		System.out.println("First Crossing : " + crossedPositions.get(0));
	}
}