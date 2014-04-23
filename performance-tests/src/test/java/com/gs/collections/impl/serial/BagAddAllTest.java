/*
 * Copyright 2014 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.impl.serial;

import java.util.concurrent.TimeUnit;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.impl.bag.mutable.HashBag;
import com.gs.collections.impl.list.Interval;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BagAddAllTest
{
    private static final int SIZE = 1000;
    private final Multiset<Integer> integersGuava = HashMultiset.create(Interval.oneTo(SIZE));
    private final MutableBag<Integer> integersGSC = Interval.oneTo(SIZE).toBag();

    @GenerateMicroBenchmark
    public void guavaAddAll()
    {
        Multiset<Integer> result = HashMultiset.create();
        for (int i = 0; i < 1000; i++)
        {
            result.addAll(this.integersGuava);
        }
    }

    @GenerateMicroBenchmark
    public void gscAddAll()
    {
        MutableBag<Integer> result = HashBag.newBag();
        for (int i = 0; i < 1000; i++)
        {
            result.addAll(this.integersGSC);
        }
    }
}
