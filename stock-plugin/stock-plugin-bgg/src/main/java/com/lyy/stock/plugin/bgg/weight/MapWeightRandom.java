package com.lyy.stock.plugin.bgg.weight;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Author:
 * @createTime: 2023/03/22 15:00:14
 * @version:
 * @Description:
 */
public class MapWeightRandom<K, V extends Number>{

    private final TreeMap<Double, K> weightMap = new TreeMap<>();

    public MapWeightRandom(List<Pair<K, V>> pairList) {
        for (Pair<K, V> pair : pairList) {
            double value = pair.getValue().doubleValue();
            if (value <= 0) {
                continue;
            }
            double lastWeight = weightMap.size() == 0 ? 0 : weightMap.lastKey();
            weightMap.put(value + lastWeight, pair.getKey());
        }
    }

    public K random() {
        if (MapUtils.isEmpty(weightMap)) {
            return null;
        }
        double randomWeight = weightMap.lastKey() * Math.random();
        SortedMap<Double, K> tailMap = weightMap.tailMap(randomWeight, false);
        return weightMap.get(tailMap.firstKey());
    }
}
