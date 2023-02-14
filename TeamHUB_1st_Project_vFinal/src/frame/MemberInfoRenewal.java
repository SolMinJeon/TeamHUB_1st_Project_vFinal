package frame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import login.LoginPanel;

public class MemberInfoRenewal {
	//메모장 삭제
	public static void main(String[] args)throws IOException{
		MemberInfoRenewal memberList = new MemberInfoRenewal();
		memberList.filterAndWriteToFile();
	}
	
	public void filterAndWriteToFile() throws IOException {
		List<String> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
		String line;
		
		while ((line = reader.readLine()) != null) {
			if (!line.startsWith(LoginPanel.lid+"/")) {
				lines.add(line);
			}
		}
		
		reader.close();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("MemberList.txt"));
		for (String newLine : lines) {
			writer.write(newLine + System.lineSeparator());
		}
		writer.close();
	}
	
}
