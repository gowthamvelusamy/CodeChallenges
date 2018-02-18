package com.gv.challenges.CombinationSummation;

import java.util.*;

public class Combinations {

    public static void main(String args[]){
        List<Integer> itemsList=new ArrayList<Integer>();
        itemsList.add(1);
        itemsList.add(2);
        itemsList.add(3);
        itemsList.add(4);
        itemsList.add(5);
        itemsList.add(6);
        Integer expectedSum=6;
       System.out.println(new Combinations().determineCombinations(itemsList,expectedSum));

    }

    private List<List<Integer>> determineCombinations(final List<Integer> listOfItems,final Integer expectedSum){
        final List<List<Integer>> combinationsResultingInSum=new ArrayList<List<Integer>>();
        //sorting the items will introduce significance performance improvement by eliminating multiple loops
        Collections.sort(listOfItems);
        final List<Integer> eligibleItems=determineEligibleItems(combinationsResultingInSum,listOfItems,expectedSum);
        // At this point the item that is exactly equal to expected sum is part of the overall valid combo
        // moreover all the eligible items are available.
        List<Integer> validCombination=new ArrayList<Integer>();
        for(final Integer beginningItem : eligibleItems){
            List<Integer> toBeIteratedItems=new ArrayList<Integer>(eligibleItems);
            toBeIteratedItems.remove(beginningItem);
            Integer iterationWidth=1;
            while(iterationWidth.intValue()<eligibleItems.size()){
                Integer innerIterationStartPos=0;
                while (innerIterationStartPos<toBeIteratedItems.size()){
                    validCombination= buildCombination(beginningItem, toBeIteratedItems,expectedSum, iterationWidth,innerIterationStartPos );
                    if(! validCombination.isEmpty()){
                        combinationsResultingInSum.add(validCombination);
                    }
                    innerIterationStartPos=innerIterationStartPos+1;
                }
            iterationWidth=iterationWidth+1;
            }
        }
       return combinationsResultingInSum;
    }

    private List<Integer> buildCombination(final Integer beginningItem,final List<Integer> toBeIteratedItemsList,
                                           final Integer expectedSum,final Integer iterationWidth,Integer iterationIndex ){


        Integer growingSum=0;
        Integer traversedDepth=0;
        Integer iteratedItem=null;
        final List<Integer> validCombination=new ArrayList<Integer>();
        validCombination.add(beginningItem);
        growingSum=beginningItem;

        while((iterationIndex.intValue() < toBeIteratedItemsList.size()) ){
            if(traversedDepth.intValue() < iterationWidth.intValue()){
                iteratedItem=toBeIteratedItemsList.get(iterationIndex.intValue());
                if(iteratedItem.intValue() !=beginningItem.intValue()){
                    growingSum = growingSum + iteratedItem;
                    if (growingSum.intValue() == expectedSum.intValue()) {
                        validCombination.add(iteratedItem);
                        traversedDepth = traversedDepth+1;
                        if(traversedDepth.intValue() != iterationWidth.intValue()){
                           validCombination.clear();
                        }
                        return validCombination;
                    } else if (growingSum.intValue() > expectedSum.intValue()) {
                        validCombination.clear();
                        return validCombination;
                    } else {
                        validCombination.add(iteratedItem);
                        traversedDepth = traversedDepth+1;
                    }
                }
            }else{
                validCombination.clear();
                return validCombination;
            }
            iterationIndex = iterationIndex+1;
        }
        validCombination.clear();
        return validCombination;
    }

    private List<Integer> determineEligibleItems(final List<List<Integer>> combinationsResultingInSum,
                                                 final List<Integer> listOfItems,final Integer expectedSum){
        final List<Integer> eligibleItems=new ArrayList<Integer>();
        final List<Integer> validCombination=new ArrayList<Integer>();
        for (final Integer listItem: listOfItems) {
            if(listItem.intValue()==expectedSum.intValue()){
                // the item is numerically equal to the expected sum
                validCombination.add(listItem);
                combinationsResultingInSum.add(validCombination);
            }else if(listItem.intValue()<expectedSum.intValue()){
                eligibleItems.add(listItem);
                // The item itself is less than the overall sum so it can be combined.
            }else{
                //The list is a sorted list so no point in itrating furthr if the item is exceeding the sum.
                break;
            }
        }
       return eligibleItems;
    }
}
