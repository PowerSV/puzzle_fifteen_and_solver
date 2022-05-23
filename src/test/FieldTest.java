package test;

import org.junit.Test;

import sample.Field;

import static org.junit.Assert.assertArrayEquals;

public class FieldTest {
    private Field field = new Field();

    @Test
    public void move() {
        field = new Field();
        field.moveRight();
        assertArrayEquals(field.getField(),new byte[][] {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 0}, {4, 8, 12, 15}});
        field.moveRight();
        assertArrayEquals(field.getField(),new byte[][] {{1, 5, 9, 13}, {2, 6, 10, 0}, {3, 7, 11, 14}, {4, 8, 12, 15}});
        field.moveDown();
        assertArrayEquals(field.getField(),new byte[][] {{1, 5, 9, 13}, {2, 6, 0, 10}, {3, 7, 11, 14}, {4, 8, 12, 15}});
        field.moveLeft();
        assertArrayEquals(field.getField(),new byte[][] {{1, 5, 9, 13}, {2, 6, 11, 10}, {3, 7, 0, 14}, {4, 8, 12, 15}});
        field.moveUp();
        assertArrayEquals(field.getField(),new byte[][] {{1, 5, 9, 13}, {2, 6, 11, 10}, {3, 7, 14, 0}, {4, 8, 12, 15}});
        field.moveUp();
        assertArrayEquals(field.getField(),new byte[][] {{1, 5, 9, 13}, {2, 6, 11, 10}, {3, 7, 14, 0}, {4, 8, 12, 15}});
    }
}
