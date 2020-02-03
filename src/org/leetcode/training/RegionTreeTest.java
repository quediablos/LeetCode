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
        root = regionTree.insert(root,-5,-2,10,0);
        root = regionTree.insert(root,5,5,7,7);
        root = regionTree.insert(root,1,1,1,1);

        Node newNode = new Node(4,4,6,6);
        assertTrue(regionTree.overlapSearch(root, newNode));

        int x=0;

    }
}
