package giftshunter;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * @version 0.1.0 2010-12-17
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
public class Santa extends JComponent
{
    public Santa()
    {
        gifts = new ArrayList<Rectangle2D>();
        current = null;

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());
    }

    public void paintGift(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        for (Rectangle2D gift: gifts)
        {
            g2.draw(gift);
        }
    }

    public Rectangle2D find(Point2D p)
    {
        for (Rectangle2D gift: gifts)
        {
            if (gift.contains(p))
            {
                return gift;
            }
        }
        return null;
    }

    public void add(Point2D p)
    {
        double x = p.getX();
        double y = p.getY();

        current = new Rectangle2D.Double(x - SIDELENGTH / 2,
                                         y - SIDELENGTH / 2,
                                         SIDELENGTH,
                                         SIDELENGTH);
        gifts.add(current);
        repaint();
    }

    public void remove(Rectangle2D gift)
    {
        if (gift == null)
        {
            return;
        }
        if (gift == current)
        {
            current = null;
        }
        gifts.remove(gift);
        repaint();
    }

    private static final int SIDELENGTH = 100;
    private ArrayList<Rectangle2D> gifts;
    private Rectangle2D current;

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent event)
        {
            Rectangle2D current = find(event.getPoint());
            if(current == null)
            {
                add(event.getPoint());
            }
        }

        @Override
        public void mouseClicked(MouseEvent event)
        {
            current = find(event.getPoint());
            if (current == null)
            {
                add(event.getPoint());
            }
        }
    }

    private class MouseMotionHandler implements MouseMotionListener
    {
        public void mouseMoved(MouseEvent event)
        {
            if (find(event.getPoint()) == null)
            {
                setCursor(Cursor.getDefaultCursor());
            }
            else
            {
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }

        public void mouseDragged(MouseEvent event)
        {
            if (current != null)
            {
                int x = event.getX();
                int y = event.getY();

                current.setFrame(x - SIDELENGTH / 2,
                                 y - SIDELENGTH / 2,
                                 SIDELENGTH,
                                 SIDELENGTH);
                repaint();
            }
        }
    }
}
