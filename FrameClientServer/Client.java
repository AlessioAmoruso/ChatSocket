package FrameClientServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		ClientFrame client = new ClientFrame();
		while(true)
		{
			System.out.println(client.getCheckIndirizzo()+" 1");
			if(client.getCheckIndirizzo())
			{
				Socket clientSocket = new Socket(client.getIndirizzo(), 9080);
				OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
				BufferedWriter bw = new BufferedWriter(osw);
				PrintWriter out = new PrintWriter(bw, true);
				client.setPrintWriter(out);
				//InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
		    	//BufferedReader br = new BufferedReader(isr);
				while(true)
				{
					//if(text.equalsIgnoreCase("x"))
					//	break;
					//String s = br.readLine();
					//client.write(s);
					System.out.println(client.getClose()+" 2");
					if(client.getClose())
					{
						System.out.println("qui2");
						break;
					}
				}
				clientSocket.close();
				break;
			}
		}

	}

}