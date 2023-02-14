package main;

import java.io.IOException;

import frame.FrameBase;
import login.LoginPanel;

public class Run {

	public static void main(String[] args) throws IOException {
		//TeamHUB_1st_Project_vFinal
		FrameBase.getInstance(new LoginPanel());

	}
}
