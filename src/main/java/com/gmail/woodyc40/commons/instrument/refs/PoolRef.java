/*
 * Copyright 2014 AgentTroll
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

package com.gmail.woodyc40.commons.instrument.refs;

import com.gmail.woodyc40.commons.collect.HashStructSet;
import javassist.bytecode.ConstPool;

import java.util.Set;

/**
 * The reference to the entire constant pool itself
 *
 * @author AgentTroll
 * @version 1.0
 */
public class PoolRef {
    private final Set<ConstantRef> refSet = new HashStructSet<>();

    public PoolRef(ConstPool pool) { // TODO
    }

    public void addRef(ConstantRef ref) {
        this.refSet.add(ref);
    }
}
