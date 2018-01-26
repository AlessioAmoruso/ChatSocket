package FrameClientServer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		boolean checkConnection = true;
		ServerSocket sSocket = new ServerSocket(9080);
		ServerFrame server = new ServerFrame();
		
	    while(true)
	    {
	    	Socket clientSocket = sSocket.accept();
	    	InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
	    	//OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
	    	BufferedReader in = new BufferedReader(isr);
	    	//BufferedWriter bw = new BufferedWriter(osw);
	    	//PrintWriter out = new PrintWriter(bw, true);
	    	System.out.println(InetAddress.getLocalHost().getHostAddress());
	    	if(checkConnection)
	    	{
	    		server.write("Client connesso al server");
	    		//out.println("Ti sei connesso al server : " + InetAddress.getLocalHost().getHostAddress());
	    		checkConnection = false;
	    	}
	    	while(true)
	    	{
	    		String s = in.readLine();
	    		System.out.println("Client : "+s);
	    		if(!s.equalsIgnoreCase("/close"))
	    		{
		    		server.write("Client disconnesso dal server ");
	    			clientSocket.close();
	    			checkConnection = true;
	    			break;
	    		}
	    		else
	    			server.write("Client : "+s);
	    	}
	    }
	}
}
