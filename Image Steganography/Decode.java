import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Decode
{
	public static void main(String args[])
	{
		BufferedImage img = null; 
		File f = null; 
		//read image 
		try
		{ 
			f = new File("C:\\Users\\hp\\Pictures\\output44.png"); 
			img = ImageIO.read(f); 
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		}
		
		System.out.println("DECODE");
		
		int i = 0, j = 0;
		int count = 0;
		int itr = 0;
		String dm = "";
		
		while(itr != 43)
		{
			int p = img.getRGB(i,j);
			
			//get alpha
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
			char at8 = r1.charAt(7);
			int at8int = at8 - '0';
			int res = at7int ^ 0;
			
			if(res == at8int)
				dm = dm.concat("0");
			else
				dm = dm.concat("1");
			
			count += 1;
			
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
			char gat8 = g1.charAt(7);
			int gat8int = gat8 - '0';
			int res2 = gat7int ^ 0;
			
			if(res2 == gat8int)
				dm += 0;
			else
				dm += 1;
			
			count += 1;

			// get blue 
			int b = p & 0xff;
			
			if(count < 127)
			{
				String b1 = Integer.toBinaryString(b);
				int z3 = 8 - b1.length();
				for(int y=0;y<z3;y++)
				{
					b1 = '0' + b1;
				}
			
				char bat7 = b1.charAt(6);
				int bat7int = bat7 - '0';
				char bat8 = b1.charAt(7);
				int bat8int = bat8 - '0';
				int res3 = bat7int ^ 0;
				
				if(res3 == bat8int)
					dm += 0;
				else
					dm += 1;
				
				count += 1;
			}
			
			itr++;
			j++;
		}
		
		System.out.println("Message: " + dm);
	}
}
