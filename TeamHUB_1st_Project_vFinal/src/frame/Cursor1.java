package frame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;


public class Cursor1 {

	public Cursor Cursor1() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("src/마우스커서.png");
		Point point = new Point(0, 25);
		// 새로운 custom 커서(image cursor, Point hotSpot, String name)
		Cursor cursor = tk.createCustomCursor(cursorimage, point, null);
		return cursor;
	}
	
	public Cursor Cursor2() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image cursorimage = tk.getImage("src/cash.png");
		Point point = new Point(0, 25);
		// 새로운 custom 커서(image cursor, Point hotSpot, String name)
		Cursor cursor = tk.createCustomCursor(cursorimage, point, null);
		return cursor;
	}
}
