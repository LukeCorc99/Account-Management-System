import java.net.Socket;
import java.io.*;


public class ServerThread extends Thread{

	Socket myConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	String message1;
	String message2;
	String message3;
	Lib library;
	
	public ServerThread(Socket s, Lib lib)
	{
		myConnection = s;
		library = lib;
	}
	
	public void run()
	{
		try
		{
			out = new ObjectOutputStream(myConnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(myConnection.getInputStream());
		
			//Server Comms
			do
			{
				sendMessage("Please enter 1 to ADD A BOOK or 2 to SEARCH FOR A BOOK or 3 for a book listing");
				message = (String)in.readObject();
			
				if(message.equalsIgnoreCase("1"))
				{
					sendMessage("Please enter the book title");
					message1 = (String)in.readObject();
					
					sendMessage("Please enter the book author");
					message2 = (String)in.readObject();
					
					sendMessage("Please enter the book price");
					message3 = (String)in.readObject();
					
					library.addBook(message1, message2, message3);
				}
				
				else if(message.equalsIgnoreCase("2"))
				{
					sendMessage("Please enter the book title");
					message1 = (String)in.readObject();
					
					String result = library.searchBook(message1);
					sendMessage(result);
				}
				
				else if(message.equalsIgnoreCase("3"))
				{
					String[] temp = library.listOfBooks();
					sendMessage(""+temp.length);
					
					for(int i=0;i<temp.length;i++)
					{
						sendMessage(temp[i]);
					}
					
				}
				
				sendMessage("Please enter 1 to repeat");
				message1 = (String)in.readObject();
				
			}while(message1.equalsIgnoreCase("1"));
			
			in.close();
			out.close();
		}
		catch(ClassNotFoundException classnot)
		{
					System.err.println("Data received in unknown format");
		}
		catch(IOException e)
		{
			
		}
		
		
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}