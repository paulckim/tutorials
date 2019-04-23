package com.snippets.datastructures.trees.bst.redblack;

import com.snippets.datastructures.trees.bst.AbstractTree;

public class RecursiveRedBlackTree<T extends Comparable<? super T>> extends AbstractTree<T> {

    private enum SubTreeState {
        BALANCED, RECOLOR,
        LL, LR, RR, RL
    }

    @Override
    public void add(T value) {
        // EDGE-CASE 1:
        RBTreeNode<T> grandparent = (RBTreeNode) this.root;
        if(null == grandparent) {
            this.root = new RBTreeNode<>(value, Color.BLACK);
            ++size;
            return;
        }
        // EDGE-CASE 2:
        if(1 == size && addChild(grandparent, value))
            return;
        // To continue, grandparent and parent cannot be equal to the value
        if(isValueEqual(grandparent, value)) return;
        // Begin Recursive Insertion:
        this.root = insert(grandparent, new RBTreeNode<>(value));
        colorRoot();
    }

    @Override
    public void remove(T value) {
        // TODO: implement later
    }

    private RBTreeNode<T> insert(RBTreeNode<T> grandparent, RBTreeNode<T> insertSubtree) {
        // If the child matches the value, the node already exists in the tree:
        RBTreeNode<T> parent = isNodeLeft(insertSubtree, grandparent)
                ? grandparent.getLeft() : grandparent.getRight();
        if(isNodeEqual(insertSubtree, parent)) return grandparent;
        RBTreeNode<T> child = isNodeLeft(insertSubtree, parent)
                ? parent.getLeft() : parent.getRight();
        if(null == child) {
            if(isNodeLeft(insertSubtree, parent)) parent.setLeft(insertSubtree);
            else parent.setRight(insertSubtree);
            ++size;
            return balance(insertSubtree, parent, grandparent);
        }
        RBTreeNode<T> subtreeRoot = insert(parent, insertSubtree);
        if(isNodeLeft(subtreeRoot, grandparent))
            grandparent.setLeft(subtreeRoot);
        else
            grandparent.setRight(subtreeRoot);
        RBTreeNode<T> postChild = isNodeLeft(subtreeRoot, insertSubtree)
                ? subtreeRoot.getLeft() : subtreeRoot.getRight();
        return balance(postChild, subtreeRoot, grandparent);
    }

    private RBTreeNode<T> balance(RBTreeNode<T> child, RBTreeNode<T> parent, RBTreeNode<T> grandparent) {
        switch (getBalanceState(child, parent, grandparent)) {
            case LL:
                return rotateLeftLeft(parent, grandparent);
            case LR:
                return rotateLeftRight(child, parent, grandparent);
            case RL:
                return rotateRightLeft(child, parent, grandparent);
            case RR:
                return rotateRightRight(parent, grandparent);
            case RECOLOR:
                recolor(grandparent);
            default:
                return grandparent;
        }
    }

    private SubTreeState getBalanceState(RBTreeNode<T> child, RBTreeNode<T> parent, RBTreeNode<T> grandparent) {
        if(isNodeBlack(child) || isNodeBlack(parent)) return SubTreeState.BALANCED;
        if(!isNodeBlack(child)
                && !isNodeBlack(grandparent.getLeft())
                && !isNodeBlack(grandparent.getRight()))
            return SubTreeState.RECOLOR;
        if(isNodeLeft(parent, grandparent)) {
            if(isNodeLeft(child, parent)) return SubTreeState.LL;
            else return SubTreeState.LR;
        }
        else {
            if(isNodeLeft(child, parent)) return SubTreeState.RL;
            else return SubTreeState.RR;
        }
    }

    /********************
     * Recoloring Rule:
     ********************/

    private void recolor(RBTreeNode<T> grandparent) {
        grandparent.setColor(Color.RED);
        RBTreeNode<T> left = grandparent.getLeft();
        RBTreeNode<T> right = grandparent.getRight();
        left.setColor(Color.BLACK);
        right.setColor(Color.BLACK);
    }

    /********************
     * Rotation Rule:
     ********************/

    private RBTreeNode<T> rotateLeftLeft(RBTreeNode<T> parent, RBTreeNode<T> grandparent) {
        grandparent.setLeft(parent.getRight());
        parent.setRight(grandparent);
        colorRotation(parent);
        return parent;
    }

    private RBTreeNode<T> rotateLeftRight(RBTreeNode<T> child, RBTreeNode<T> parent, RBTreeNode<T> grandparent) {
        parent.setRight(child.getLeft());
        grandparent.setLeft(parent.getRight());
        child.setLeft(parent);
        child.setRight(grandparent);
        colorRotation(child);
        return child;
    }

    private RBTreeNode<T> rotateRightLeft(RBTreeNode<T> child, RBTreeNode<T> parent, RBTreeNode<T> grandparent) {
        parent.setLeft(child.getRight());
        grandparent.setRight(child.getLeft());
        child.setLeft(grandparent);
        child.setRight(parent);
        colorRotation(child);
        return child;
    }

    private RBTreeNode<T> rotateRightRight(RBTreeNode<T> parent, RBTreeNode<T> grandparent) {
        grandparent.setRight(parent.getLeft());
        parent.setLeft(grandparent);
        colorRotation(parent);
        return parent;
    }

    private boolean addChild(RBTreeNode<T> parent, T value) {
        if(null == parent || isValueEqual(parent, value))
            return false;
        if(isValueLess(parent, value))
            parent.setLeft(new RBTreeNode<>(value));
        else
            parent.setRight(new RBTreeNode<>(value));
        ++size;
        return true;
    }

    /**
     * Takes into account null nodes (considered black).
     *
     * @param node the node to get the color of
     * @return true if the node is null or BLACK. Otherwise, returns false.
     */
    private boolean isNodeBlack(RBTreeNode<T> node) {
        return null == node || node.getColor().equals(Color.BLACK);
    }

    private boolean isNodeLeft(RBTreeNode<T> child, RBTreeNode<T> parent) {
        return child.getValue().compareTo(parent.getValue()) < 0;
    }

    private boolean isNodeEqual(RBTreeNode<T> child, RBTreeNode<T> parent) {
        return child.getValue().compareTo(parent.getValue()) == 0;
    }

    private boolean isValueLess(RBTreeNode<T> root, T value) {
        return value.compareTo(root.getValue()) < 0;
    }

    private boolean isValueEqual(RBTreeNode<T> root, T value) {
        return value.compareTo(root.getValue()) == 0;
    }

    private void colorRotation(RBTreeNode<T> root) {
        root.setColor(Color.BLACK);
        root.getLeft().setColor(Color.RED);
        root.getRight().setColor(Color.RED);
    }

    private void colorRoot() {
        // always keep root's color black:
        Color rootColor = ((RBTreeNode<T>) this.root).getColor();
        if(rootColor.equals(Color.RED))
            ((RBTreeNode<T>) this.root).setColor(Color.BLACK);
    }

}
