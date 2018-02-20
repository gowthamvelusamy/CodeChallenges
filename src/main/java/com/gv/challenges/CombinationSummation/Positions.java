package com.gv.challenges.CombinationSummation;

import java.util.*;

public class Positions {

    public  static void main(String a[]){
        System.out.println("Start"+ System.currentTimeMillis());
        Positions positions=new Positions();
        List<Integer> inputItems=new ArrayList<Integer>();
        inputItems.add(1);
        inputItems.add(3);
        inputItems.add(2);
        inputItems.add(5);
        inputItems.add(4);
        System.out.println(positions.findValidCombinations(positions.buildPositionsMap(inputItems),inputItems,6));
        System.out.println("Stop"+ System.currentTimeMillis());
    }

    private Map<Integer,Set<HashSet<Integer>>> buildPositionsMap(final List<Integer> inputItems){
        final Map<Integer,Set<HashSet<Integer>>> positionsMap=new HashMap<Integer, Set<HashSet<Integer>>>();
        final HashSet<Integer> actualPositions=new HashSet<Integer>();
        for(Integer index=0;index<inputItems.size();index++){
            actualPositions.add(index);
        }
        Set<HashSet<Integer>> listOfPreviousPositionBuckets=new HashSet<HashSet<Integer>>();
        for(Integer numberOfPositionsInBucket:actualPositions){
            positionsMap.put(numberOfPositionsInBucket,buildBucketsForPosition(actualPositions,numberOfPositionsInBucket,listOfPreviousPositionBuckets));
            listOfPreviousPositionBuckets=positionsMap.get(numberOfPositionsInBucket);
        }
        return positionsMap;
    }

    private List<Set<Integer>> findValidCombinations(final Map<Integer,Set<HashSet<Integer>>> positionsMap,final List<Integer> inputItems,
                                                     final Integer expectedSum){
        List<Set<Integer>> validCombinations=new ArrayList<Set<Integer>>();
        Set<HashSet<Integer>> possiblePositions=null;
        for(Map.Entry<Integer, Set<HashSet<Integer>>> allCombinations: positionsMap.entrySet()){
            possiblePositions=allCombinations.getValue();
            for(HashSet<Integer> candidate:possiblePositions){
               if(sumOfItemsUsingPositions(candidate,inputItems).intValue() == expectedSum.intValue()){
                   validCombinations.add(candidate);
               }
            }
        }
        return validCombinations;
    }
    private Integer sumOfItemsUsingPositions(final HashSet<Integer> positions,final List<Integer> items){
        Integer sum=0;
        for(final Integer position:positions){
            sum  += items.get(position);
        }
        return sum;
    }

    private Set<HashSet<Integer>> buildBucketsForPosition(final HashSet<Integer> actualPositions,final Integer numberOfPositionsInBucket,
                                         final Set<HashSet<Integer>> listOfPreviousPositionBuckets){
        Set<HashSet<Integer>> listOfCurrentPositionBuckets = new HashSet<HashSet<Integer>>();
        if(listOfPreviousPositionBuckets.isEmpty()) {
            for(final Integer actualPosition:actualPositions) {
                final HashSet<Integer> positionBucket=new HashSet<Integer>();
                positionBucket.add(actualPosition);
                listOfCurrentPositionBuckets.add(positionBucket);
            }
        }else{
            for(final HashSet<Integer> aPositionBucketAtPreviousSize:listOfPreviousPositionBuckets) {
                listOfCurrentPositionBuckets=buildPositionBucketAtSize(aPositionBucketAtPreviousSize,actualPositions,listOfCurrentPositionBuckets);
            }
        }
        return listOfCurrentPositionBuckets;
        }

    private Set<HashSet<Integer>> buildPositionBucketAtSize(HashSet<Integer> aPositionBucketAtPreviousSize,
                                                            HashSet<Integer> actualPositions, Set<HashSet<Integer>> listOfNewPositionBuckets) {

        HashSet<Integer> aPositionBucketAtNewSize=null;
        for(Integer actualPosition:actualPositions) {
            if(!aPositionBucketAtPreviousSize.contains(actualPosition)) {
                aPositionBucketAtNewSize=new HashSet<Integer>();
                aPositionBucketAtNewSize.addAll(aPositionBucketAtPreviousSize);
                aPositionBucketAtNewSize.add(actualPosition);
                listOfNewPositionBuckets.add(aPositionBucketAtNewSize);
            }
        }
        return listOfNewPositionBuckets;
    }
}
