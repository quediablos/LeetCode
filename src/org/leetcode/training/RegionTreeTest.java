package org.leetcode.training;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RegionTreeTest
{

    @Test
    public void test_1()
    {
        RegionTree regionTree = new RegionTree();
        Node root = null;
        root = regionTree.insert(root,0,0,3,3);
        regionTree.insert(root,-5,-2,0,4);
        regionTree.insert(root,-8,-4,-5,-2);
        regionTree.insert(root,-2,4,3,6);
        regionTree.insert(root,-7,3,-4,6);

        Node newNode = new Node(-7,3,-4,6);
        assertTrue(regionTree.overlapSearch(root, newNode));

        int x=0;

    }

    @Test
    public void test_2()
    {
        RegionTree regionTree = new RegionTree();
        Node root = null;

        for (int x= -100; x<= 99; x++)
        {
            for (int y=-100; y<=99; y++)
            {
                root = regionTree.insert(root,x,y,x+1,y+1);
            }
        }

        Node newNode = new Node(-200,-200,-10,-10);
        assertTrue(regionTree.overlapSearch(root, newNode));

        int x=0;

    }

    @Test
    public void test_3()
    {
        RegionTree regionTree = new RegionTree();
        Node root = null;

        for (int x= -250; x<= 249; x++)
        {
            for (int y=-5; y<=4; y++)
            {
                try {
                    root = regionTree.insert(root,x,y,x+1,y+1);
                }
                catch (Error e)
                {
                    x =0;
                }
            }
        }

        Node newNode = new Node(100,0,101,1);
        assertTrue(regionTree.overlapSearch(root, newNode));

        int x=0;

    }
}
