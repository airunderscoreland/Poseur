/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poseur.shapes;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Element;
import poseur.PoseurSettings;

/**
 *
 * @author rakeebsf
 */
public class PoseurLine extends PoseurShape {

    // THIS STORES ALL THE GEOMETRY FOR THIS line
    private Line2D.Double geometry;

    private static Line2D.Double sharedGeometry = new Line2D.Double();
    
    /**
     * PoseurLine objects are constructed with their geometry, which
     * can be updated later via service methods.
     * 
     * @param initGeometry The geometry to associate with this rectangle.
     */
    public PoseurLine( Line2D.Double initGeometry)
    {
        super();
        geometry = initGeometry;
    }

    /**
     * This static method constructs and returns a new line with an
     * x location of the poseSpaceX argument, a y location of poseSpaceY,
     * and a width and height of 0.
     * 
     * @param poseSpaceX The requested x value for the new, factory
     * built PoseurRectangle object.
     * 
     * @param poseSpaceY The requested y value for the new, factory
     * built PoseurRectangle object.
     * 
     * @return A constructed PoseurRectangle.
     */    
    public static PoseurLine factoryBuildLine(int poseSpaceX, int poseSpaceY)
    {
        Line2D.Double lin = new Line2D.Double(poseSpaceX, poseSpaceY, poseSpaceX, poseSpaceY);
        return new PoseurLine(lin);        
    }

    /**
     * Accessor method for getting this shape type.
     * 
     * @return The PoseurShapeType associated with this object.
     */    
    @Override
    public PoseurShapeType getShapeType() { return PoseurShapeType.LINE; }

        
    /**
     * This method tests if the testPoint argument is inside this
     * line. If it does, we return true, if not, false.
     * 
     * @param testPoint The point we want to test and see if it is
     * inside this rectangle
     * 
     * @return true if the point is inside this rectangle, false otherwise.
     */
    @Override
    public boolean containsPoint(Point2D testPoint)
    {
        //was the point within the tolerance?
        return geometry.ptLineDistSq(testPoint) < PoseurSettings.LINE_SELECTION_TOLERANCE*PoseurSettings.LINE_SELECTION_TOLERANCE;
    }
   
    /**
     * This method renders this line to whatever context the g2 argument
     * comes from. 
     * 
     * @param g2 The graphics context for rendering. It may refer to that
     * of a canvas or an image.
     * 
     * @param poseOriginX The x coordinate location of the pose box.
     * 
     * @param poseOriginY The y coordinate location of the pose box.
     * 
     * @param zoomLevel Used for scaling all that gets rendered.
     * 
     * @param isSelected Selected items are highlighted.
     */   
    @Override
    public void render( Graphics2D g2, int 
                        poseOriginX, int poseOriginY, 
                        float zoomLevel, 
                        boolean isSelected)
    {
        sharedGeometry.x1 = poseOriginX + (geometry.x1 * zoomLevel);
        sharedGeometry.y1 = poseOriginY + (geometry.y1 * zoomLevel);
        sharedGeometry.x2 = poseOriginX + (geometry.x2 * zoomLevel);
        sharedGeometry.y2 = poseOriginY + (geometry.y2 * zoomLevel);
        
        renderShape(g2, sharedGeometry, isSelected);
    }
    
    /**
     * This method makes a clone, i.e. a duplicate, of this rectangle. This
     * is useful for cut/copy/paste types of operations in applications.
     * 
     * @return A constructed object that is identical to this one.
     */    
    @Override
    public PoseurShape clone()
    {
        Line2D.Double copyGeometry = (Line2D.Double)geometry.clone();
        
        // SINCE Color AND Stroke ARE IMMUTABLE,
        // WE DON'T MIND SHARING THEM 
        PoseurShape copy = new PoseurLine( copyGeometry);
        //copy.alpha = this.alpha; //do we need this?
        copy.fillColor = this.fillColor;
        copy.outlineColor = this.outlineColor;
        copy.outlineThickness = this.outlineThickness;
        
        return copy;
    }
 
    /**
     * This method moves this shape to the x, y location without doing
     * any error checking on whether it's a good location or not.
     * 
     * @param x The x coordinate of where to move this rectangle.
     * 
     * @param y The y coordinate of where to move this rectangle.
     */
    @Override
    public void move(int x, int y)
    {
        double oldx1 = geometry.x1;
        double oldy1 = geometry.y1;
        geometry.x1 = (double)x;
        geometry.y1 = (double)y;
        geometry.x2 += (x-oldx1);
        geometry.y2 += (y-oldy1);
        
    }

    /**
     * This is a smarter method for moving this rectangle, it considers
     * the pose area and prevents it from being moved off the pose area
     * by clamping at the edges.
     * 
     * @param incX The amount to move this rectangle in the x-axis.
     * 
     * @param incY The amount to move this rectangle in the y-axis.
     * 
     * @param poseArea The box in the middle of the rendering canvas
     * where the shapes are being rendered.
     */
    @Override
    public void moveShape(  int incX, int incY, 
                            Rectangle2D.Double poseArea) 
    {
        // MOVE THE SHAPE
        geometry.x1 += incX;
        geometry.y1 += incY;
        geometry.x2 += incX;
        geometry.y2 += incY;
        
        // AND NOW CLAMP IT SO IT DOESN'T GO OFF THE EDGE
        
        // CLAMP ON LEFT SIDE
        if (geometry.x1 < 0)
        {
            geometry.x1 = 0;
            geometry.x2 -= incX; //undo change
            geometry.y2 -= incY;
            geometry.y1 -= incY;
        }
        if (geometry.x2 < 0) 
        {
            geometry.x2 = 0;
            geometry.y1 -= incY;
            geometry.x1 -= incX;
            geometry.y2 -= incY;
        }
        // CLAMP ON RIGHT
        if (geometry.x1 > poseArea.width)
        {
            geometry.x1 = poseArea.width;
            geometry.x2 -= incX; //undo change
            geometry.y2 -= incY;
            geometry.y1 -= incY;
        }
        if (geometry.x2 > poseArea.width)
        {
            geometry.x2 = poseArea.width;
            geometry.y1 -= incY;
            geometry.x1 -= incX;
            geometry.y2 -= incY;
        }
        // CLAMP ON TOP
        if (geometry.y1 < 0)
        {
            geometry.y1 = 0;
            geometry.y2 -= incY;
            geometry.x1 -= incX;
            geometry.x2 -= incX;
        }
        if (geometry.y2 < 0 )
        {
            geometry.y2 = 0;
            geometry.y1 -= incY;
            geometry.x1 -= incX;
            geometry.x2 -= incX;
        }
        // CLAMP ON BOTTOM
        if (geometry.y1 > poseArea.height)
        {
            geometry.y1 = poseArea.height;
            geometry.y2 -= incY;
            geometry.x1 -= incX;
            geometry.x2 -= incX;
        }
        if (geometry.y2 > poseArea.height)
        {
            geometry.y2 = poseArea.height;
            geometry.y1 -= incY;
            geometry.x1 -= incX;
            geometry.x2 -= incX;
        }
    }
    
    /**
     * This method tests to see if the x,y arguments would be valid
     * second point  for a line in progress.
     * 
     * @param x The x-axis coordinate for the test point.
     * 
     * @param y The y-axis coordinate for the test point.
     * 
     * @return true if (x,y) would be a valid (x2, y2)
     * point based on where this line is currently located.
     */  
    @Override
    public boolean completesValidShape(int x, int y)
    {
        // WE ONLY LET SHAPES BE BUILT TOP LEFT TO BOTTOM RIGHT
        /*if ( (x < geometry.x2) ||
             (y < geometry.y2))
        {
            return false;
        }
        else
        {
            return true;
        }*/
        return true;
    }
    
    /**
     * This method helps to update the line that's being
     * sized, testing to make sure it doesn't draw in illegal 
     * coordinates.
     * 
     * @param updateX The x-axis coordinate for the update point.
     * 
     * @param updateY The y-axis coordinate for the update point.
     */  
    @Override
    public void updateShapeInProgress(int updateX, int updateY)
    {
        geometry.x2 = updateX;
        geometry.y2 = updateY;
    }
    
    /**
     * This method helps to build a .pose file. Rectangles know what data
     * they have, so this fills in the geometryNode argument DOC element
     * with the rectangle data that would be needed to recreate it when
     * it's loaded back from the .pose (xml) file.
     * 
     * @param geometryNode The node where we'll put attributes regarding
     * the geometry of this rectangle.
     */    
    @Override
    public void addNodeData(Element geometryNode)
    {
        geometryNode.setAttribute(PoseurSettings.SHAPE_TYPE_ATTRIBUTE, getShapeType().name());
        geometryNode.setAttribute(PoseurSettings.X1_ATTRIBUTE, "" + geometry.x1);
        geometryNode.setAttribute(PoseurSettings.Y1_ATTRIBUTE, "" + geometry.y1);
        geometryNode.setAttribute(PoseurSettings.X2_ATTRIBUTE, "" + geometry.x2);
        geometryNode.setAttribute(PoseurSettings.Y2_ATTRIBUTE, "" + geometry.y2);
    }
    
    
}
