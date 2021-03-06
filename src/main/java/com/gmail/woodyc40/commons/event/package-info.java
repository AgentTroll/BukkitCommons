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

/**
 * Event framework used to listen and call for events that are not suited for use by using the BukkitAPI
 * {@link org.bukkit.plugin.PluginManager}.
 *
 * <p>This event framework is thread safe.</p>
 *
 * <p>Since 1.1, the event framework is handled from a single thread. All multi-threaded calls are still safe to use,
 * however, due to thread confinement, the receiver is not required to synchronize.</p>
 */

package com.gmail.woodyc40.commons.event;
