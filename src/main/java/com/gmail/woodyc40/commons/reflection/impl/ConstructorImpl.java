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

package com.gmail.woodyc40.commons.reflection.impl;

import com.gmail.woodyc40.commons.reflection.ConstructorManager;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Actual implementation of {@link com.gmail.woodyc40.commons.reflection.ConstructorManager} used for fast reflection
 *
 * @param <T> the type the constructor creates an instance of
 * @author AgentTroll
 * @version 1.0
 * @since 1.0
 */
class ConstructorImpl<T> implements ConstructorManager<T> {
    /** The langreflect version of the constructor */
    private final Constructor<T>      constructor;
    /** The sun version of the constructor */
    private final ConstructorAccessor accessor;

    /**
     * Wraps the Constructor for management by this implementation
     *
     * @param constructor the Constructor to wrap
     */
    public ConstructorImpl(Constructor<T> constructor) {
        this.constructor = constructor;
        this.accessor = ReflectAccess.getREFLECTION_FACTORY().newConstructorAccessor(this.constructor);
    }

    @Override public T createInstance(Object... args) {
        try {
            return (T) this.accessor.newInstance(args);
        } catch (IllegalArgumentException | InvocationTargetException | InstantiationException x) {
            x.printStackTrace();
        }

        return null;
    }

    @Override public Constructor<T> raw() {
        return this.constructor;
    }
}
