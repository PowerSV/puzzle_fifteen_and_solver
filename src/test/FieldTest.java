package test;

import org.junit.Before;
import org.junit.Test;

import sample.Field;

import static org.junit.Assert.assertArrayEquals;

public class FieldTest {

    @Test
    public void moveTest() {
        Field field = new Field();
        field.moveRight();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 9, 13},
                {2, 6, 10, 14},
                {3, 7, 11, 0},
                {4, 8, 12, 15}});

        field.moveRight();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 9, 13},
                {2, 6, 10, 0},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveRight();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 9, 0},
                {2, 6, 10, 13},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveRight();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 9, 0},
                {2, 6, 10, 13},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveDown();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 0, 9},
                {2, 6, 10, 13},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveLeft();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 10, 9},
                {2, 6, 0, 13},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveUp();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 10, 9},
                {2, 6, 13, 0},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveUp();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 10, 9},
                {2, 6, 13, 0},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});

        field.moveDown();
        assertArrayEquals(field.getField(), new byte[][] {
                {1, 5, 10, 9},
                {2, 6, 0, 13},
                {3, 7, 11, 14},
                {4, 8, 12, 15}});
    }
}
