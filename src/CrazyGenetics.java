
import robocode.*;
import robocode.util.Utils;
import java.awt.Color;


public class CrazyGenetics extends AdvancedRobot {

 boolean movingForward;

	public void run() {



		while(true) {
			setAhead(10);
			movingForward = true;

         setTurnRight(0);

			waitFor(new TurnCompleteCondition(this));

			setTurnLeft(0);

			waitFor(new TurnCompleteCondition(this));

			setTurnRight(0);

			waitFor(new TurnCompleteCondition(this));
		}

	}
 public void onHitWall(HitWallEvent e) {
 	reverseDirection();
 }

 public void reverseDirection() {
 	if (movingForward) {
			setBack(10);
			movingForward = false;
		} else {
			setAhead(10);
			movingForward = true;
		}
	}
	public void onScannedRobot(ScannedRobotEvent e) {
 fire(1);
	}

 public void onHitRobot(HitRobotEvent e) {
 	if (e.isMyFault()) {
 		reverseDirection();
		}
 }
}