package src.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class Logo {

    private String text;
    char pix;
    String fontName;
    int fontStyle, fontSize;
    

    public Logo (String theText, String theFontName, int theFontStyle, int theFontSize){
        this.text = theText;
        this.fontName = theFontName;
        this.fontStyle = theFontStyle;
        this.fontSize = theFontSize;
    }
    public void printLogo() {

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font(fontName, fontStyle, fontSize);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(text);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        g2d = img.createGraphics();
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setBackground(Color.BLACK);
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, 0, fm.getAscent());
        g2d.dispose();

        Raster imageRaster = img.getData();    

             for (int column = 0; column < height; column++) {
       for (int row = 0; row < width; row++) {
        if ( imageRaster.getSample(row, column, 0) == 0) {
            pix = ' ';
        } 
        else
        {   pix = 'X' ;
        }
            System.out.print( pix); }
                   System.out.println();
                 }   
                 
    }

}

/* Note: Part of the program was borrowed from some internet websites, such as: 

https://stackoverflow.com/questions/18800717/convert-text-content-to-image
https://stackoverflow.com/questions/4989603/convert-an-image-to-binary-data-0s-and-1s-in-java
*/