import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Binarization {
	public static BufferedImage Binarization(BufferedImage edgeImg, int threshold) {
		
		int width = edgeImg.getWidth();
		int height = edgeImg.getHeight();
		
		//create binary image
		BufferedImage binary = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_BINARY);
		
		WritableRaster edgeRaster = edgeImg.getRaster();
		WritableRaster binaryRaster = binary.getRaster();
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				
				int edgeVal = edgeRaster.getSample(x, y, 0);
				
				int binaryVal = (edgeVal > threshold) ?255 :0;
				
				binaryRaster.setSample(x, y, 0, binaryVal);
			}
		}
		
		return binary;
	}

}
