package etcjane;

import java.lang.*;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AviatoConn {
	
	private static Socket s;
	
	public static void main(String[]args) {
		
		String reply;
		Date date = new Date();
		 try
	        {
	            s = new Socket("production", 20000);
	            BufferedReader from_exchange = new BufferedReader(new InputStreamReader(s.getInputStream()));
	            PrintWriter to_exchange = new PrintWriter(s.getOutputStream(), true);

	            to_exchange.println("HELLO AVIATO");
	            while((reply = from_exchange.readLine()) != null) {
		            reply = reply.trim();
//		            SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss:SSSSSS");
//		            String formattedDate = sdf.format(date);
		            long tmp = System.nanoTime();
		            if (reply.startsWith("BOOK")) {
		            	String[] splits = reply.split("BUY|SELL");
		            	int i=0;
		            	for (String str : splits) {
		            		if (i == 1){
		            			System.out.print(tmp + ": BUY ");
		 
		            		}
		            		else if (i == 2) {
		            			System.out.print(tmp + ": SELL ");
		            		}
		            		System.out.printf("%s\n", str);
		            		i++;
		            	}
		            }
		            System.out.printf("%o: %s\n", tmp, reply);
		            System.out.println();
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace(System.out);
	        } finally {
	        	try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}
}