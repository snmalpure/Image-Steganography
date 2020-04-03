import java.io.File; 
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 

public class Encode
{ 
	public static void main(String args[])throws IOException 
	{ 
		BufferedImage img = null; 
		File f = null; 

		//read image 
		try
		{ 
			f = new File("C:\\Users\\hp\\Pictures\\tig.jpg"); 
			img = ImageIO.read(f); 
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		} 

		int width = img.getWidth(); 
		int height = img.getHeight(); 
		
		String msg = "";
		System.out.println("ENCODE");
		//System.out.println("Enter message");
		//Scanner sc = new Scanner(System.in);
		//msg = sc.next();
		
		Random rand = new Random();
		String str = "";
		for(int y=0;y<128;y++)
		{
			int num = rand.nextInt(2);
			msg += num;
		}
		System.out.println(msg);
		
		int i = 0, j = 0;
		int count = 0;
		int it = 0;
		
		while(it != 43)
		{
			int p = img.getRGB(i,j); 
	
			// get alpha 
			int a = (p>>24) & 0xff; 
	
			// get red 
			int r = (p>>16) & 0xff;
			
			String r1 = Integer.toBinaryString(r);
			int z = 8 - r1.length();
			for(int y=0;y<z;y++)
			{
				r1 = '0' + r1;
			}
			
			char at7 = r1.charAt(6);
			int at7int = at7 - '0';
			char bit = msg.charAt(3 * count);
			int bitint = bit - '0';
			int res = at7int ^ bitint;
			r1 = r1.substring(0,7) + res;
			r = Integer.parseInt(r1,2);
	
			// get green 
			int g = (p>>8) & 0xff;
			
			String g1 = Integer.toBinaryString(g);
			int z2 = 8 - g1.length();
			for(int y=0;y<z2;y++)
			{
				g1 = '0' + g1;
			}
			
			char gat7 = g1.charAt(6);
			int gat7int = gat7 - '0';
			char gbit = msg.charAt(3 * count + 1);
			int gbitint = gbit - '0';
			int res2 = gat7int ^ gbitint;
			g1 = g1.substring(0,7) + res2;
			g = Integer.parseInt(g1,2);
			
			// get blue 
			int b = p & 0xff;
			
			String b1 = Integer.toBinaryString(b);
			int z3 = 8 - b1.length();
			for(int y=0;y<z3;y++)
			{
				b1 = '0' + b1;
			}
			
			char bat7 = b1.charAt(6);
			int bat7int = bat7 - '0';
			if(3*count+2 < 127)
			{
				char bbit = msg.charAt(3 * count + 2);
				int bbitint = bbit - '0';
				int res3 = bat7int ^ bbitint;
				b1 = b1.substring(0,7) + res3;
				b = Integer.parseInt(b1,2);
			}
	
			//set the pixel value 
			p = (a<<24) | (r<<16) | (g<<8) | b; 
	        img.setRGB(i, j, p);
	
			j++;
			count++;
			it++;
		}
		
		//write image 
		try
		{ 
			f = new File("C:\\Users\\hp\\Pictures\\output44.png"); 
			ImageIO.write(img, "png", f); 
			
			System.out.println("Message Encoded.");
			System.out.println("Stego Image Created.");
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		}
	} 
} 
