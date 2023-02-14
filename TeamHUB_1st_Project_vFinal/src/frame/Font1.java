package frame;

import java.awt.Font;

public class Font1 { // xxxxx.setFont(new Font1().bigFont());
						// xxxxx.setFont(new Font1().smallFont());
	public Font bigFont() {

		Font bigFont = new Font("안동엄마까투리", Font.BOLD, 30);
		return bigFont;

	}

	public Font smallFont() {

		Font bigFont = new Font("안동엄마까투리", Font.BOLD, 15);
		return bigFont;

	}

}
