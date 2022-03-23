package com.nuvalence.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RectangleTest {

    @Test
    public void subLineAdjacentRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 10, 100, 150);
        Rectangle rectangleTwo = new Rectangle(110, 45, 50, 75);

        Assertions.assertTrue(rectangleOne.adjacent(rectangleTwo));
    }

    @Test
    public void properAdjacentRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 10, 100, 150);
        Rectangle rectangleTwo = new Rectangle(110, 10, 100, 150);

        Assertions.assertTrue(rectangleOne.adjacent(rectangleTwo));
    }

    @Test
    public void partialAdjacentRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 10, 100, 150);
        Rectangle rectangleTwo = new Rectangle(110, 110, 50, 150);

        Assertions.assertTrue(rectangleOne.adjacent(rectangleTwo));
    }

    @Test
    public void notAdjacentRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 10, 100, 150);
        Rectangle rectangleTwo = new Rectangle(130, 45, 50, 75);

        Assertions.assertFalse(rectangleOne.adjacent(rectangleTwo));
    }

    @Test
    public void containsRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 200, 100, 150);
        Rectangle rectangleTwo = new Rectangle(35, 240, 50, 75);

        Assertions.assertTrue(rectangleOne.contains(rectangleTwo));
    }

    @Test
    public void notContainsRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 200, 100, 150);
        Rectangle rectangleTwo = new Rectangle(130, 240, 50, 75);

        Assertions.assertFalse(rectangleOne.contains(rectangleTwo));
    }

    @Test
    public void notContainsButIntersectingRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 200, 100, 150);
        Rectangle rectangleTwo = new Rectangle(80, 240, 50, 75);

        Assertions.assertFalse(rectangleOne.contains(rectangleTwo));
    }

    @Test
    public void intersectingRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 400, 100, 150);
        Rectangle rectangleTwo = new Rectangle(20, 410, 100, 150);

        Assertions.assertTrue(rectangleOne.intersects(rectangleTwo));
    }

    @Test
    public void notIntersectingRectangleTest() {
        Rectangle rectangleOne = new Rectangle(10, 400, 100, 150);
        Rectangle rectangleTwo = new Rectangle(130, 410, 100, 150);

        Assertions.assertFalse(rectangleOne.intersects(rectangleTwo));
    }
}