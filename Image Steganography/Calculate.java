import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Calculate
{
    public static double logbase10(double x)
    {
        return Math.log(x) / Math.log(10);
    }
    
    public static void main(String args[])
    {
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		File f = null; 

		//read image 
		try
		{ 
			f = new File("C:\\Users\\hp\\Pictures\\tig.jpg"); 
			img1 = ImageIO.read(f);
			f = new File("C:\\Users\\hp\\Pictures\\output44.png");
			img2 = ImageIO.read(f);
		} 
		catch(IOException e) 
		{ 
			System.out.println(e); 
		} 

		int width = img1.getWidth(); 
		int height = img1.getHeight(); 
		
		Raster r1 = img1.getRaster();
		Raster r2 = img2.getRaster();
		
		double mse = 0;
		
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				mse += Math.pow(r1.getSample(i, j, 0) - r2.getSample(i, j, 0), 2);
			}
		}
		
		mse /= (double) (width * height);
	    double psnr = 10.0 * logbase10(Math.pow(255, 2) / mse);
	    
	    System.out.println("MSE = " + mse);
	    System.out.println("PSNR = " + psnr + " dB");
    }
}
