/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql4java;

import java.awt.Dimension;
import java.awt.Rectangle;

/**
 *
 * @author Lic. Jorge Isaac Vásquez Valenciano
 */
public class NanosoftLayOut {

    private final int jframe_width;
    private final int jframe_height;
    private final int object_margin;
    private int components_height;
    
    /**
     * NanosofotLayOut: By Lic. Isaac Vásquez
     * @param w JFrame (Main) Width
     * @param h JFrame (Main) Height
     * @param margin Elements Margin (top-bottom) between them.
     */
    public NanosoftLayOut(int w, int h, int margin) 
    {
        this.jframe_height = h;
        this.jframe_width = w;
        this.object_margin = margin;
    }
    
    /**
     * Retorna los valores inicializados en el constructor.
     * @return
     */
    public Dimension getJFrameDimension() 
    {
        return new Dimension(this.jframe_width, this.jframe_height);
    }
    
    public String getNanosoftLayOut() 
    {
        return null;
    }
            
    
    /**
     * Recibe el ancho del elemento para posicionarlo con el ancho del jframe
     * @param element_width
     * @return el centro del JFrame
     */
    public int getXCenter( int element_width) 
    {
        return (this.jframe_width/2) - element_width/2;
    }

    /**
     * Recibe el ancho del elemento, calcula la longitud siguiente en Y del JFrame
     * @param element_height
     * @return
     */
    public int getYCenter(int element_height) 
    {
        this.components_height += element_height + this.object_margin;
        return this.components_height;
    }
    
    /**
     * Recibe el ancho y el largo del elemento y lo posiciona automáticamente
     * @param x El ancho del elemento (width)
     * @param y El largo del elemento (height)
     * @return
     */
    public Rectangle getRectangle(int x, int y) 
    {
        return new Rectangle(this.getXCenter(x), this.getYCenter(y), x, y);
    }
    
}
