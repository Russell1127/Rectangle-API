package com.nuvalence.core;

public class Rectangle {
    private float x;
    private float y;
    private float width;
    private float height;

    public Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.height = 1;
    }

    public Rectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getMaxX() {
        return getX() + getWidth();
    }

    public float getMaxY() {
        return getY() + getHeight();
    }

    public boolean isEmpty() {
        return (width <= 0.0f) || (height <= 0.0f);
    }

    public void setRectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rectangle rectangle) {
        return this.intersects(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public boolean intersects(float x, float y, float width, float height) {
        if (isEmpty() || width <= 0 || height <= 0) {
            return false;
        }

        float x0 = getX();
        float y0 = getY();
        return (x + width > x0 &&
                y + height > y0 &&
                x < x0 + getWidth() &&
                y < y0 + getHeight());
    }

    public boolean contains(Rectangle rectangle) {
        return this.contains(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public boolean contains(float x, float y, float width, float height) {
        if (isEmpty() || width <= 0 || height <= 0) {
            return false;
        }
        float x0 = getX();
        float y0 = getY();
        return (x >= x0 &&
                y >= y0 &&
                (x + width) <= x0 + getWidth() &&
                (y + height) <= y0 + getHeight());
    }

    public boolean adjacent(Rectangle rectangle) {
        return this.adjacent(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    public boolean adjacent(float x, float y, float width, float height) {
        if (isEmpty() || width <= 0 || height <= 0) {
            return false;
        }

        return (x >= getX() && x <= getMaxX() && (getY() == y || getY() == y + height || getMaxY() == y || getMaxY() == y + height)) ||
                (y >= getY() && y <= getMaxY() && (getX() == x || getX() == x + width || getMaxX() == x || getMaxX() == x + width));
    }

    public String toString() {
        return getClass().getName()
                + "[x=" + x +
                ",y=" + y +
                ",width=" + width +
                ",height=" + height + "]";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Rectangle rectangle) {
            return ((getX() == rectangle.getX()) &&
                    (getY() == rectangle.getY()) &&
                    (getWidth() == rectangle.getWidth()) &&
                    (getHeight() == rectangle.getHeight()));
        }
        return false;
    }
}
