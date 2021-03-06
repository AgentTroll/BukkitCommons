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

package com.gmail.woodyc40.commons.event;

/**
 * Handles events called by the framework <p> <p>MUST BE ANNOTATED WITH {@link com.gmail.woodyc40.commons.event
 * .Handler}!</p> <p> <p>This interface is considered to be thread safe, however, implementations of it are not
 * guaranteed to be thread safe.</p>
 *
 * @author AgentTroll
 * @version 1.0
 * @since 1.0
 */
public interface EventHandler {
    /**
     * Override this method and perform x action on event when it is called
     *
     * @param event the called event, casted.
     */
    void handle(CustomEvent event);
}
