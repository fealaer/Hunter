package hunter;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

/**
 * @version 0.1.0
 * @author Pushkarev Andrey [fealaer@gmail.com]
 */
class Thrower {

    public Thrower(int amount, int width, int height) {
        nTargets = amount;
        this.width = width;
        this.height = height;

        TargetImages.add("img/targets/gift1.png");
        TargetImages.add("img/targets/gift2.png");
        TargetImages.add("img/targets/gift3.png");
        TargetImages.add("img/targets/gift4.png");
        TargetImages.add("img/targets/gift5.png");

        imagesSize = TargetImages.getSize();

        int part = nTargets / 5;



        for (int i = 0; i < part; i++) {
            add();
        }
    }

    public int getTargetsSize() {
        return targets.size();
    }

    public void drawTargets(Graphics2D g2) {
        if (nTargets > 0) {
            add();
        }
        for (int i = 0; i < targets.size(); i++) {
            Target tr = (Target) targets.get(i);
            if (!tr.move(g2)) {
                targets.remove(tr);
            }
        }
    }

    synchronized public boolean shoot(Point2D p) {
        Target tr = this.find(p);
        if (tr == null) {
            return false;
        }
        remove(tr);
        return true;
    }

    /**
     * Finds the first target containing a point.
     *
     * @param p a point
     * @return the first target that contains p
     */
    public Target find(Point2D p) {
        int count = targets.size();
        for (int i = 0; i < count; i++) {
            Target tr = (Target) targets.get(i);
            Rectangle2D square = tr.getTargetSquare();
            if (square.contains((Point) p)) {
                return tr;
            }
        }
        return null;
    }

    /**
     * Adds a target to the collection.
     */
    private void add() {
        int sidelength = random(SIDELENGTH - (SIDELENGTH / 2)) + SIDELENGTH / 2;
        int x = random(width - SIDELENGTH) + sidelength;
        int y = 0;
        int imageIndex = random(imagesSize);
        int velocity = (sidelength / 5) + 1;
        int pause = random(height / 2) + SIDELENGTH;
        targets.add(new Target(x, y, sidelength, pause, imageIndex, velocity, height));
        --nTargets;
    }

    /**
     *  Obtain a randomly-selected integer between 0 and a limit.
     *
     *  @param limit one more than the largest integer that can be returned
     *
     *  @return an integer ranging from 0 through limit-1
     */
    private int random(int limit) {
        return (int) (Math.random() * limit);
    }

    /**
     * Removes a square from the collection.
     * @param s the square to remove
     */
    private void remove(Target s) {
        if (s == null) {
            return;
        }

        targets.remove(s);
    }
    /**
     *  Side length of largest Target. Target inscribed in square.
     */
    private static final int SIDELENGTH = 40;
    /**
     * Data structure for managing Target objects.
     */
    private Vector targets = new Vector();
    /**
     * Number of Targets objects managed by this game
     */
    private int nTargets;
    /**
     *  Frame height. Passed to each Target object's constructor and also
     *  used to determine image buffer height.
     */
    private int height;
    /**
     *  Frame height. Passed to each Target object's constructor and also
     *  used to determine target destroy
     */
    private int width;
    private int imagesSize;
}
